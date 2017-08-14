package System;


import Messages.ATPMsg;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public class ChannelManager
{
    private final List<Client> clients = new ArrayList<>();
    private final CommQueue commQueue = new CommQueue();
    private Thread processThread;
    private final int threadCount = 5;
    private final List<Thread> tList = new ArrayList<>();
    private final AtomicBoolean threadWorking = new AtomicBoolean(false);
    private static final Lock indexLock = new ReentrantLock();
    private static final Condition condIndex = indexLock.newCondition();
    
    private static class InstanceHolder {
        private static final ChannelManager INSTANCE = new ChannelManager();
    }
    
    protected ChannelManager()
    {
        
    }
    
    public static ChannelManager getInstance()
    {
        return InstanceHolder.INSTANCE;
    }
    
    public void init()
    {
        commQueue.init();
        commQueue.startTimeoutThread();
        
        for (int i=0; i<threadCount; ++i)
        {
            Thread t = new Thread() {
                @Override
                public void run() {
                    _processWorker(500);
                }  
            };
            tList.add(t);
        }
        
    }
    
    public void startProcess()
    {
        threadWorking.set(true);
        tList.parallelStream().forEach((Thread t) -> t.start());
    }
    
    public void stopProcess()
    {
        threadWorking.set(false);
    }
    
    public boolean registerClient(Client client)
    {
        boolean ret = clients.add(client);
        
        if (ret) {
            VA_DEBUG.INFO("[ChannelManager] Client ("+client.getAppId().name()+") registered.", true, 1);
        }
        else {
            VA_DEBUG.ERROR("[ChannelManager] Failed client ("+client.getAppId().name()+") register.", true);
        }
        
        return ret;
    }
    
    public boolean unregisterClient(Client client)
    {
        boolean ret = clients.removeIf((Client cli) -> cli.getAppId() == client.getAppId());
        
        if (ret) {
            VA_DEBUG.INFO("[ChannelManager] Client ("+client.getAppId().name()+") unregistered.", true, 1);
        }
        else {
            VA_DEBUG.ERROR("[ChannelManager] Failed client ("+client.getAppId().name()+") unregister.", true);
        }
        
        return ret;
    }
    
    public boolean isClientRegistered(ApplicationId appId)
    {
        boolean ret = clients.stream()
            .filter(elem -> elem.getAppId().equals(appId))
            .findFirst()
            .isPresent();
        
        return ret;
    }
    
    public Client getClient(ApplicationId appId)
    {
        Client cli = clients.stream()
            .filter(elem -> elem.getAppId() == appId)
            .findFirst()
            .orElse(null);
        
        return cli;
    }
    
    public int send(ATPMsg msg)
    {
        commQueue.addObject(new CommObject(msg), msg.getPriority().getValue(), 3000);
        
        _notifyTask();
        
        return 0;
    }
    
    private void _processWorker(int millisecondPeriod)
    {
        VA_DEBUG.INFO("[ChannelManager] Process thread started", true);
        while(threadWorking.get())
        {
            _waitTask();
            
            CommObject item = commQueue.getHighestPrioObject();
            while(item != null)
            {
                VA_DEBUG.INFO("[ChannelManager] Process msg "+item.printString(), true, 3);
                
                boolean ret = false;
                Client targetClient = getClient(item.getMsg().getTargetId());
                if (targetClient != null)
                {
                    ret = targetClient.handleRequest(item.getMsg());
                }
                
                item.setCommComplete(ret, null);
                
                // Next msg
                item = commQueue.getHighestPrioObject();
            }
            
        }
        VA_DEBUG.INFO("[ChannelManager] Process thread stopped", true);
    }
    
    private void _waitTask()
    {
        indexLock.lock();
        try {
            condIndex.await();
        }
        catch (InterruptedException ex) {
            VA_DEBUG.ERROR("[ChannelManager] InterruptedException: "+ex.getMessage(), true);
        }
        finally { indexLock.unlock(); }
    }
    
    private void _notifyTask()
    {
        indexLock.lock();
        try {
            condIndex.signal();
        }
        finally { indexLock.unlock(); }
    }
}

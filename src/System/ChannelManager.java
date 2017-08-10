package System;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    private static class InstanceHolder {
        private static final ChannelManager INSTANCE = new ChannelManager();
    }
    
    public static ChannelManager getInstance()
    {
        return InstanceHolder.INSTANCE;
    }
    
    public boolean registerClient(Client client)
    {
        boolean ret = clients.add(client);
        
        if (ret) {
            VA_DEBUG.INFO("[ChannelManager] Client ("+client.getAppId().name()+") registered.", true);
        }
        else {
            VA_DEBUG.ERROR("[ChannelManager] Failed client ("+client.getAppId().name()+") register.", true);
        }
        
        return ret;
    }
    
    public boolean unregisterClient(Client client)
    {
        boolean ret = clients.removeIf((Client cli) -> cli.getAppId() == client.getAppId());
        
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
        
        return 0;
    }
}

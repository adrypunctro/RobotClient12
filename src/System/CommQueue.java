/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 *
 * @author ASimionescu
 */
public class CommQueue
{
    private Comparator<CommQueueElem> comparator = new CommQueueComparator();
    private PriorityQueue<CommQueueElem> queue = new PriorityQueue<>(10, comparator);
    private AtomicBoolean threadWorking = new AtomicBoolean(false);
    private Thread timeoutThread;
    
    public CommQueue()
    {
        
    }
    
    public void init()
    {
        timeoutThread = new Thread() {
            @Override
            public void run() {
                _timeoutWorker(500);
            }  
        };
    }
    
    public synchronized void addObject(CommObject item, int priority, long timeout)
    {
        CommQueueElem elem = new CommQueueElem(item, priority, timeout);
        queue.add(elem);
    }
    
    public synchronized CommObject getHighestPrioObject()
    {
        CommObject item = null;
        if (!queue.isEmpty())
        {
            item = queue.poll().item;
        }
        return item;
    }
    
    public void startTimeoutThread()
    {
        threadWorking.set(true);
        timeoutThread.start();
    }
    
    public void stopTimeoutThread()
    {
        threadWorking.set(false);
    }
    
    private void _timeoutWorker(int millisecondPeriod)
    {
        VA_DEBUG.INFO("[TRANSPORT] Timeout thread started", true);
        while(threadWorking.get())
        {
            try { Thread.sleep(millisecondPeriod); } catch (InterruptedException e) {
                System.out.println(e);
            }
            
            // remove all comm objects which have timed out (their timeout moment is in the past)
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long now = timestamp.getTime();
                    
            synchronized(this) {
                queue.removeIf(
                    (CommQueueElem elem) -> {
                        if (elem.timeout < now) {
                            elem.item.setCommComplete(false, null);
                            return true;
                        }
                        return false;
                    }
                );
            }
        }
        VA_DEBUG.INFO("[TRANSPORT] Timeout thread stopped", true);
    }
    
    public class CommQueueComparator implements Comparator<CommQueueElem>
    {
        @Override
        public int compare(CommQueueElem x, CommQueueElem y)
        {
            // Assume neither string is null. Real code should
            // probably be more robust
            // You could also just return x.length() - y.length(),
            // which would be more efficient.
            if (x.priority < y.priority)
            {
                return -1;
            }
            if (x.priority > y.priority)
            {
                return 1;
            }
            return 0;
        }
    }
    
    public class CommQueueElem
    {
        final CommObject item;
        final int priority;
        final long timeout;
        
        public CommQueueElem(CommObject item, int priority, long timeout)
        {
            this.item = item;
            this.priority = priority;
            this.timeout = timeout;
        }
    }
}

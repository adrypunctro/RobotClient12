/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author ASimionescu
 */
public abstract class Monitor
{
    private Thread monitoringThread;
    private final AtomicBoolean monitoring = new AtomicBoolean(false);
    
    public void init()
    {
        monitoringThread = new Thread() {
            @Override
            public void run() {
                _processWorker(500);
            }  
        };
    }
    
    public void startMonitoring()
    {
        monitoring.set(true);
        monitoringThread.start();
    }
    
    public void stopMonitoring()
    {
        monitoring.set(false);
    }
    
    public boolean isAlive()
    {
        return monitoring.get();
    }
    
    protected abstract void _processWorker(int millisecondPeriod);
    
}

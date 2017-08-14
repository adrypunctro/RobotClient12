/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Messages.TouchDetectedCommand;
import Visual.Visual;

/**
 *
 * @author ASimionescu
 */
public class TouchMonitor
    extends Monitor
{

    @Override
    protected void _processWorker(int millisecondPeriod)
    {
        VA_DEBUG.INFO("[TOUCH MONITOR] Monitoring started", true, 3);
        while(isAlive())
        {
            // PROCESS IMAGE
            try { Thread.sleep(500); } catch (InterruptedException e) {
                System.out.println(e);
            }
            
            int min = 0;
            int max = 6;
            int detect = (int) ((Math.random() * (max+1-min)) + min);
            
            if (detect == 0)
            {
                VA_DEBUG.INFO("[TOUCH MONITOR] Touch was detected.", true, 2);
                
                _touchDetected();
            }
        }
        VA_DEBUG.INFO("[TOUCH MONITOR] Monitoring stopped", true, 3);
    }
    
    private void _touchDetected()
    {
        ChannelManager manager = ChannelManager.getInstance();
        if (manager != null)
        {
            if (manager.isClientRegistered(ApplicationId.TOUCH))
            {
                TouchDetectedCommand msg = new TouchDetectedCommand();
                msg.setSource(ApplicationId.TOUCH);
                msg.setTarget(ApplicationId.TOUCH);
                msg.createTransactionId();

                int transId = manager.send(msg);
            }
            else
            {
                VA_DEBUG.WARNING("[TOUCH MONITOR] TOUCH client is not registered.", true);
            }
        }
        else
        {
            VA_DEBUG.WARNING("[TOUCH MONITOR] ChannelManager is null.", true);
        }
    }
}

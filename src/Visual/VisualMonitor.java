/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Messages.PersonDetectedCommand;
import System.ApplicationId;
import System.ChannelManager;
import System.Monitor;
import System.VA_DEBUG;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author ASimionescu
 */
public class VisualMonitor
    extends Monitor
{
    
    @Override
    protected void _processWorker(int millisecondPeriod)
    {
        VA_DEBUG.INFO("[VISUAL MONITOR] Monitoring started", true, 3);
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
                VA_DEBUG.INFO("[VISUAL MONITOR] Person was detected.", true, 2);
                
                _personDetected();
            }
        }
        VA_DEBUG.INFO("[VISUAL MONITOR] Monitoring stopped", true, 3);
    }
    
    public void _personDetected()
    {
        ChannelManager manager = ChannelManager.getInstance();
        if (manager != null)
        {
            if (manager.isClientRegistered(ApplicationId.VISUAL))
            {
                PersonDetectedCommand msg = new PersonDetectedCommand();
                msg.setSource(ApplicationId.VISUAL);
                msg.setTarget(ApplicationId.VISUAL);
                msg.createTransactionId();

                int transId = manager.send(msg);
            }
            else
            {
                VA_DEBUG.WARNING("[VISUAL MONITOR] VISUAL client is not registered.", true);
            }
        }
        else
        {
            VA_DEBUG.WARNING("[VISUAL MONITOR] ChannelManager is null.", true);
        }
    }
}

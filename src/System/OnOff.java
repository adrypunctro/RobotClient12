/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Memory.Memory;

/**
 *
 * @author ASimionescu
 */
public class OnOff
{
    private static class InstanceHolder {
        private static final OnOff INSTANCE = new OnOff();
    }
    
    public static OnOff getInstance()
    {
        return InstanceHolder.INSTANCE;
    }
    
    public void startProgram()
    {
        VA_DEBUG.INFO("[ONOFF] Program starting...", true);
        
        ChannelManager manager = ChannelManager.getInstance();
        
        manager.init();
        manager.startProcess();
        
        Visual visual = new Visual();
        Memory memory = new Memory();
        
        visual.registerClient();
        memory.registerClient();
        
        VA_DEBUG.INFO("[ONOFF] Program is runing.", true);
        
        while(true)
        {
            int min = 5;
            int max = 30;
            int millisecondPeriod = (int) ((Math.random() * (max+1-min)) + min);
            try { Thread.sleep(millisecondPeriod); } catch (InterruptedException e) {
                System.out.println(e);
            }
            
            visual.triggerNewPerson();
        }
        
    }
    
    
}

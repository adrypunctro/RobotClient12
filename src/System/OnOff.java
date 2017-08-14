/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Visual.VisualMonitor;
import Visual.Visual;
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
    
    public static void startProgram()
    {
        VA_DEBUG.INFO("[ONOFF] Program starting ::::::::::::::::::::::::::::::::", true, 1);
        
        // Init
        Visual visual = new Visual();
        Memory memory = new Memory();
        Touch touch = new Touch();
        
        VisualMonitor visualMonitor = new VisualMonitor();
        TouchMonitor touchMonitor = new TouchMonitor();
        
        ChannelManager manager = ChannelManager.getInstance();
        
        manager.init();
        manager.startProcess();

        visual.registerClient();
        memory.registerClient();
        touch.registerClient();
        
        visualMonitor.init();
        visualMonitor.startMonitoring();
        
        touchMonitor.init();
        touchMonitor.startMonitoring();
        
        VA_DEBUG.INFO("[ONOFF] Program is runing :::::::::::::::::::::::::::::::", true, 1);
        

        
    }
    
    
}

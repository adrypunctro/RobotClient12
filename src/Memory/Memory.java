package Memory;

import System.ATPMsg;
import System.ApplicationId;
import System.ChannelManager;
import System.Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public class Memory
    extends Client
{
    public Memory() {
        super(ApplicationId.MEMORY);
    }

    @Override
    public void handleRequest(ATPMsg msg)
    {
        // 1 - triggerNewPerson
        // 2 - searchPeople
    }
    
    @Override
    public boolean registerClient()
    {
        ChannelManager manager = ChannelManager.getInstance();
        
        if (manager != null)
        {
            return manager.registerClient(this);
        }
        
        return false;
    }
    
    @Override
    public boolean unregisterClient()
    {
        ChannelManager manager = ChannelManager.getInstance();
        
        if (manager != null)
        {
            return manager.unregisterClient(this);
        }
        
        return false;
    }
}

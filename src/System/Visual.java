package System;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public class Visual
    extends Client
{
    
    public Visual()
    {
        super(ApplicationId.VISUAL);
    }
    

    
    @Override
    public void handleRequest(ATPMsg msg)
    {
        switch(msg.getMsgType())
        {
            case memoryNewPersonResponse:
                _handleMemoryNewPersonResponse(msg);
                break;
            
            default:
                VA_DEBUG.INFO("[Visual] unknown message type received("+msg.getMsgType().name()+")", true);
                break;
        }
        
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
    
    
    
    public boolean triggerNewPerson()
    {
        ChannelManager manager = ChannelManager.getInstance();
        
        if (manager == null)
        {
            VA_DEBUG.INFO("[Visual] ChannelManager is null.", true);
            return false;
        }
        
        if (!manager.isClientRegistered(ApplicationId.MEMORY))
        {
            VA_DEBUG.INFO("[Visual] MEMORY is not registered.", true);
            return false;
        }
        
        NewPersonRequestMsg msg = new NewPersonRequestMsg();
        msg.setSource(ApplicationId.VISUAL);
        msg.setTarget(ApplicationId.MEMORY);
        
        int transId = manager.send(msg);

        return true;
    }
    
    
    public boolean triggerSearchPerson()
    {
        
        return true;
    }
    
    private void _handleMemoryNewPersonResponse(ATPMsg msg)
    {
        
    }
}

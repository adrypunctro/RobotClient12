package System;

import Messages.ATPMsg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public abstract class Client
{
    private final ApplicationId appId;
    
    public abstract boolean handleRequest(ATPMsg msg);
    public abstract boolean registerClient();
    public abstract boolean unregisterClient();
    
    protected Client(ApplicationId appId)
    {
        this.appId = appId;
    }
    
    public ApplicationId getAppId()
    {
        return appId;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof Client)
        {
            isEqual = (this.appId == ((Client) object).appId);
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.appId.ordinal();
    }
}

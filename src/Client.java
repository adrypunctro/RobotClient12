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
    
    protected Client(ApplicationId appId)
    {
        this.appId = appId;
    }
    
    public ApplicationId getAppId()
    {
        return appId;
    }
    public abstract void handleRequest();
}


import java.util.List;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public class ChannelManager
{
    private List<Client> clients;
    
    private static class InstanceHolder {
        private static final ChannelManager instance = new ChannelManager();
    }
    
    public static ChannelManager getInstance()
    {
        return InstanceHolder.instance;
    }
    
    public boolean registerClient(Client client)
    {
        boolean ret = clients.add(client);
        
        return ret;
    }
    
    public boolean unregisterClient(Client client)
    {
        boolean ret = clients.removeIf((Client cli) -> cli.getAppId() == client.getAppId());
        
        return ret;
    }
    
    public boolean isClientRegistered(ApplicationId appId)
    {
        boolean ret = clients.stream()
            .filter(elem -> elem.getAppId().equals(appId))
            .findFirst()
            .isPresent();
        
        return ret;
    }
    
    public Client getClient(ApplicationId appId)
    {
        Client cli = clients.stream()
            .filter(elem -> elem.getAppId() == appId)
            .findFirst()
            .orElse(null);
        
        return cli;
    }
}

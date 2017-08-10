/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import System.ChannelManager;
import System.Visual;
import System.Client;
import System.ApplicationId;
import Memory.Memory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASimionescu
 */
public class ChannelManagerTest {
    
    public ChannelManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void registerUnregisterClient()
    {
        Visual visual = new Visual();
        
        assertTrue("Failed registerClient.", visual.registerClient());
        assertTrue("Failed isClientRegistered. Expected true.", ChannelManager.getInstance().isClientRegistered(ApplicationId.VISUAL));
        
        Client client = ChannelManager.getInstance().getClient(ApplicationId.VISUAL);
        assertEquals("Wrong client AppId.", client.getAppId(), ApplicationId.VISUAL);
        assertTrue("Wrong client instance type.", client instanceof Visual);
        
        assertTrue("Failed unregisterClient.", visual.unregisterClient());
        assertFalse("Failed isClientRegistered. Expected false.", ChannelManager.getInstance().isClientRegistered(ApplicationId.VISUAL));
        
    }
    
    @Test
    public void communicationClients()
    {
        Visual visual = new Visual();
        Memory memory = new Memory();
        
        assertTrue("Failed registerClient.", visual.registerClient());
        assertTrue("Failed isClientRegistered. Expected true.", ChannelManager.getInstance().isClientRegistered(ApplicationId.VISUAL));
        
        assertTrue("Failed registerClient.", memory.registerClient());
        assertTrue("Failed isClientRegistered. Expected true.", ChannelManager.getInstance().isClientRegistered(ApplicationId.MEMORY));
        
        
        
        
        Client client = ChannelManager.getInstance().getClient(ApplicationId.VISUAL);
        assertEquals("Wrong client AppId.", client.getAppId(), ApplicationId.VISUAL);
        assertTrue("Wrong client instance type.", client instanceof Visual);
        
        assertTrue("Failed unregisterClient.", visual.unregisterClient());
        assertFalse("Failed isClientRegistered. Expected false.", ChannelManager.getInstance().isClientRegistered(ApplicationId.VISUAL));
        
    }
}

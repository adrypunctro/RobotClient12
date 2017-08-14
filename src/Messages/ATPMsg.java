package Messages;

import System.ApplicationId;
import System.MessagePriority;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public abstract class ATPMsg
{
    private static final AtomicInteger TRANSACTION_ID_AUTO_INCREMENT = new AtomicInteger(1);
    private MessagePriority priority = MessagePriority.NORMAL;
    private int transactionId = 0;
    private MessageType msgType;
    private ApplicationId sourceAppId;
    private ApplicationId targetAppId;
    
    protected ATPMsg(MessageType msgType)
    {
        this.msgType = msgType;
    }
    
    public void createTransactionId()
    {
        this.transactionId = TRANSACTION_ID_AUTO_INCREMENT.getAndIncrement();
    }
    
    public void setTransactionId(int transId)
    {
        transactionId = transId;
    }
    
    public void setPriority(MessagePriority priority)
    {
        this.priority = priority;
    }
    
    public void setSource(ApplicationId appId)
    {
        this.sourceAppId = appId;
    }
    
    public void setTarget(ApplicationId appId)
    {
        this.targetAppId = appId;
    }
    
    public int getTransactionId()
    {
        return transactionId;
    }
    
    public MessagePriority getPriority()
    {
        return priority;
    }
    
    public ApplicationId getSourceId()
    {
        return sourceAppId;
    }
    
    public ApplicationId getTargetId()
    {
        return targetAppId;
    }
    
    public MessageType getMsgType()
    {
        return msgType;
    }
    
    public String printString()
    {
        return sourceAppId.name()+" > "+targetAppId.name()+" ("+msgType.name()+", transId:"+transactionId+", prior:"+priority.name()+priority.getValue()+")";
    }
}

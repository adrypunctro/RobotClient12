/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Messages.ATPMsg;

/**
 *
 * @author ASimionescu
 */
public class CommObject
{
    private ATPMsg msg;
    
    public CommObject(ATPMsg msg)
    {
        this.msg = msg;
    }
    
    public ATPMsg getMsg()
    {
        return msg;
    }
    
    public void setCommComplete(boolean status, ATPMsg reply)
    {
        
    }
    
    public String printString()
    {
        return msg.printString();
    }
}

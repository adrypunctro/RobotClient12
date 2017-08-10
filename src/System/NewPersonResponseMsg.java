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
public class NewPersonResponseMsg
    extends ATPMsg
{
    
    public NewPersonResponseMsg()
    {
        super(MessageType.memoryNewPersonResponse);
    }
}

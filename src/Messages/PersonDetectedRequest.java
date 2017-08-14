/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

/**
 *
 * @author ASimionescu
 */
public class PersonDetectedRequest
    extends ATPMsg
{
    
    public PersonDetectedRequest() {
        super(MessageType.personDetectedRequest);
    }
    
}

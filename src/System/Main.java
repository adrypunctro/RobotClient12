package System;

import Memory.Memory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASimionescu
 */
public class Main
{
    
    public static void main(String[] args)
    {
        OnOff onoff = OnOff.getInstance();
        
        onoff.startProgram();

    }
    
}

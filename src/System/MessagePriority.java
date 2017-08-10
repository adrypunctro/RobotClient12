/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

/**
 *
 * @author ASimionescu
 */
public enum MessagePriority
{
    REAL_TIME(1), HIGH(2), NORMAL(3), BEST_EFFORT(4);
    
    private final int value;
    
    MessagePriority(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}

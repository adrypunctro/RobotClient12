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
public enum MessageType
{
    onoffWakeUpRequest,
    onoffWakeUpResponse,
    onoffSleepRequest,
    onoffSleepResponse,
    onoffShutdownRequest,
    onoffShutdownResponse,
    
    memoryNewPersonRequest,
    memoryNewPersonResponse,
    memorySearchPersonRequest,
    memorySearchPersonResponse,
    memoryUpdatePersonRequest,
    memoryUpdatePersonResponse
}

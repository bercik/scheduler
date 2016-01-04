/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

/**
 *
 * @author robert
 */
public enum Day
{
    Monday, 
    Tuesday, 
    Wednesday, 
    Thursday, 
    Friday, 
    Saturday, 
    Sunday;
    
    public Day next(){
		return values()[(ordinal()+1)% values().length];
	}
	public Day previous(){
		return values()[(ordinal()-1)% values().length];
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author robert
 */
public interface IScheduleDatabase
{
    public void addItem(Item i) throws Item.OverlappingDateException;
    
    public void removeItem(Item i);
    
    public void editItem(Item oldI, Item newI) 
            throws Item.OverlappingDateException;
    
    public List<Item> getAllItems();
    
    public void save(String filename) throws IOException;
    
    public void load(String filename) throws 
            IOException, Date.BadDateException, Item.BadDatesException,
            Item.OverlappingDateException;
}

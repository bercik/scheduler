/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author robert
 */
public class ScheduleLocalDatabase implements IScheduleDatabase
{
    private final List<Item> items = new ArrayList<>();
    
    @Override
    public void addItem(Item i) throws Item.OverlappingDateException
    {
        if (checkOverlapping(i))
        {
            throw new Item.OverlappingDateException();
        }
        
        items.add(i);
    }

    @Override
    public void removeItem(Item i)
    {
        items.remove(i);
    }

    private boolean checkOverlapping(Item i)
    {
        for (Item item : items)
        {
            if (item.checkDateOverlapping(i))
            {
                return true;
            }
        }
        
        return false;
    }
    
    // jeżeli data nowego elementu nachodzi na jakąś która znajduje się już w 
    // bazie to rzuca wyjątek i nie(!) usuwa starego elementu
    @Override
    public void editItem(Item oldI, Item newI) throws 
            Item.OverlappingDateException
    {
        removeItem(oldI);
        
        try
        {
            addItem(newI);
        }
        catch (Item.OverlappingDateException ex)
        {
            addItem(oldI);
            throw ex;
        }
    }

    @Override
    public List<Item> getAllItems()
    {
        return Collections.unmodifiableList(items);
    }
}

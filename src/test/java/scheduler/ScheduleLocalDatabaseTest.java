/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robert
 */
public class ScheduleLocalDatabaseTest
{
    private static ScheduleLocalDatabase sld;
    
    public ScheduleLocalDatabaseTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
            throws Item.OverlappingDateException, Date.BadDateException, 
                Item.BadDatesException
    {
        sld = new ScheduleLocalDatabase();
        
        Item it = new Item(new Date(8, 15, Day.Thursday), 
                new Date(11, 15, Day.Thursday),
                "Układy elektroniczne Lab.", "Piotr Wiącek", "D10", "208");
        sld.addItem(it);
        
        it = new Item(new Date(13, 50, Day.Wednesday), 
                new Date(15, 20, Day.Wednesday),
                "Metody Numeryczne Lab.", "Tomasz Chwiej", "D10", "207");
        sld.addItem(it);
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of addItem method, of class ScheduleLocalDatabase.
     * @throws scheduler.Date.BadDateException
     * @throws scheduler.Item.BadDatesException
     * @throws scheduler.Item.OverlappingDateException
     */
    @Test(expected = Item.OverlappingDateException.class)
    public void testAddItem() throws Date.BadDateException,
            Item.BadDatesException, Item.OverlappingDateException
    {
        System.out.println("addItem");
        Item i = new Item(new Date(10, 30, Day.Thursday),
                new Date(12, 00, Day.Thursday), null, null, null, null);
        sld.addItem(i);
    }

    /**
     * Test of removeItem method, of class ScheduleLocalDatabase.
     * @throws scheduler.Item.BadDatesException
     * @throws scheduler.Date.BadDateException
     * @throws scheduler.Item.OverlappingDateException
     */
    @Test
    public void testAddItemGetAllItemsAndRemoveItem() throws Item.BadDatesException, 
            Date.BadDateException, Item.OverlappingDateException
    {
        System.out.println("removeItem");
        Item i = new Item(new Date(15, 30, Day.Wednesday), new Date(17, 00,
                Day.Wednesday), "Bazy danych Lab.", "Grażyna Krupińska",
                "D-10", "209");
        
        List<Item> items = sld.getAllItems();
        boolean expResult = false;
        boolean result = false;
        for (Item item : items)
        {
            if (item == i)
            {
                result = true;
            }
        }
        assertEquals(expResult, result);
        
        sld.addItem(i);
        
        items = sld.getAllItems();
        expResult = true;
        result = false;
        for (Item item : items)
        {
            if (item == i)
            {
                result = true;
            }
        }
        assertEquals(expResult, result);
        
        sld.removeItem(i);
        
        items = sld.getAllItems();
        expResult = false;
        result = false;
        for (Item item : items)
        {
            if (item == i)
            {
                result = true;
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of editItem method, of class ScheduleLocalDatabase.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditItem() throws Exception
    {
        System.out.println("editItem");
        
        Item oldI = sld.getAllItems().get(0);
        String newName = "Matematyka stosowana Lab.";
        Item newI = new Item(oldI.getStart(), oldI.getEnd(), 
                newName, "Małgorzata Zwonek", "D11", "104");
        
        sld.editItem(oldI, newI);
        
        assertEquals(sld.getAllItems().get(1).getName(), newName);
        
        oldI = sld.getAllItems().get(0);
        newI = new Item(new Date(9, 00, Day.Thursday), 
                new Date(10, 00, Day.Thursday), oldI.getName(), 
                oldI.getLeading(), oldI.getFaculty(), oldI.getRoom());
        
        boolean expResult = true;
        boolean catched = false;
        
        try
        {
            sld.editItem(oldI, newI);
        }
        catch (Item.OverlappingDateException ex)
        {
            catched = true;
            
            Item it = sld.getAllItems().get(1);
            assertEquals(it.getName(), oldI.getName());
        }
        
        assertEquals(catched, expResult);
    }
}

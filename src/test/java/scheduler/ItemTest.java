/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

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
public class ItemTest
{
    
    public ItemTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
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
     * Test of checkDateOverlapping method, of class Item.
     * @throws scheduler.Item.BadDatesException
     * @throws scheduler.Date.BadDateException
     */
    @Test
    public void testCheckDateOverlapping() 
            throws Item.BadDatesException, Date.BadDateException
    {
        System.out.println("checkDateOverlapping");
        Item instance = new Item(new Date(14, 30, Day.Monday), 
                new Date(16, 00, Day.Monday), null, null, null, null);
        
        Item anotherItem = new Item(new Date(16, 00, Day.Monday), 
                new Date(17, 30, Day.Monday), null, null, null, null);
        boolean expResult = false;
        boolean result = instance.checkDateOverlapping(anotherItem);
        assertEquals(expResult, result);
        
        anotherItem = new Item(new Date(12, 00, Day.Monday), 
                new Date(14, 00, Day.Monday), null, null, null, null);
        expResult = false;
        result = instance.checkDateOverlapping(anotherItem);
        assertEquals(expResult, result);
        
        anotherItem = new Item(new Date(14, 00, Day.Monday), 
                new Date(15, 30, Day.Monday), null, null, null, null);
        expResult = true;
        result = instance.checkDateOverlapping(anotherItem);
        assertEquals(expResult, result);
        
        anotherItem = new Item(new Date(15, 00, Day.Monday), 
                new Date(16, 30, Day.Monday), null, null, null, null);
        expResult = true;
        result = instance.checkDateOverlapping(anotherItem);
        assertEquals(expResult, result);
        
        anotherItem = new Item(new Date(13, 00, Day.Monday), 
                new Date(10, 30, Day.Thursday), null, null, null, null);
        expResult = true;
        result = instance.checkDateOverlapping(anotherItem);
        assertEquals(expResult, result);
    }
}

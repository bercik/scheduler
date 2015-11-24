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
public class DateTest
{
    private static Date instance;
    private static Date dateAfter;
    private static Date dateBefore;
    private static Date dateEqual;
    
    public DateTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass() throws Date.BadDateException
    {
        instance = new Date(10, 50, Day.Thursday);
        dateAfter = new Date(11, 42, Day.Thursday);
        dateBefore = new Date(23, 55, Day.Monday);
        dateEqual = new Date(10, 50, Day.Thursday);
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

    @Test(expected = Date.BadDateException.class)
    public void testConstructor() throws Date.BadDateException
    {
        Date junk = new Date(24, 62, Day.Monday);
    }
    
    /**
     * Test of isBefore method, of class Date.
     */
    @Test
    public void testIsBefore()
    {
        System.out.println("isBefore");
        boolean expResult = true;
        boolean result = instance.isBefore(dateAfter);
        assertEquals(expResult, result);
        
        expResult = false;
        result = instance.isBefore(dateBefore);
        assertEquals(expResult, result);
        
        result = instance.isBefore(dateEqual);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAfter method, of class Date.
     */
    @Test
    public void testIsAfter()
    {
        System.out.println("isAfter");
        boolean expResult = true;
        boolean result = instance.isAfter(dateBefore);
        assertEquals(expResult, result);
    }

    /**
     * Test of isBeforeOrEqual method, of class Date.
     */
    @Test
    public void testIsBeforeOrEqual()
    {
        System.out.println("isBeforeOrEqual");
        boolean expResult = true;
        boolean result = instance.isBeforeOrEqual(dateEqual);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAfterOrEqual method, of class Date.
     */
    @Test
    public void testIsAfterOrEqual()
    {
        System.out.println("isAfterOrEqual");
        boolean expResult = true;
        boolean result = instance.isAfterOrEqual(dateEqual);
        assertEquals(expResult, result);
    }
}

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
public class Item
{
    private final Date start;
    private final Date end;
    private final String name;
    private final String leading;
    private final String faculty;
    private final String room;

    public Item(Date start, Date end, String name, String leading, 
            String faculty, String room) throws BadDatesException
    {
        if (start.isAfterOrEqual(end))
        {
            throw new BadDatesException();
        }
        
        this.start = start;
        this.end = end;
        this.name = name;
        this.leading = leading;
        this.faculty = faculty;
        this.room = room;
    }
    
    public boolean checkDateOverlapping(Item anotherItem)
    {
        return this.start.isBefore(anotherItem.end) 
                && anotherItem.start.isBefore(this.end);
    }

    public Date getStart()
    {
        return start;
    }

    public Date getEnd()
    {
        return end;
    }

    public String getName()
    {
        return name;
    }

    public String getLeading()
    {
        return leading;
    }

    public String getFaculty()
    {
        return faculty;
    }

    public String getRoom()
    {
        return room;
    }
    
    public static class OverlappingDateException extends Exception
    {
        public OverlappingDateException()
        {
        }
    }
    
    public static class BadDatesException extends Exception
    {
        public BadDatesException()
        {
        }   
    }
}

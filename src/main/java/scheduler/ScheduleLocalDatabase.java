/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void save(String filename) throws IOException
    {
        String jarPath = getJarPath();
        String filePath = filename + jarPath;
        
        FileWriter fw = new FileWriter(filename);
        try (CSVWriter csvw = new CSVWriter(fw))
        {
            for (Item item : items)
            {
                List<String> entries = new ArrayList<>();
                entries.addAll(getStringsFromDate(item.getStart()));
                entries.addAll(getStringsFromDate(item.getEnd()));
                entries.add(item.getName());
                entries.add(item.getLeading());
                entries.add(item.getRoom());
                entries.add(item.getFaculty());
                
                csvw.writeNext(entries.toArray(new String[0]));
            }
        }
    }
    
    private List<String> getStringsFromDate(Date date)
    {
        List<String> result = new ArrayList<>();
        result.add(Integer.toString(date.getHour()));
        result.add(Integer.toString(date.getMinute()));
        result.add(date.getDay().toString());
        
        return  result;
    }
    
    private Date getDateFromStrings(String[] strings) 
            throws Date.BadDateException
    {
        int hour = Integer.parseInt(strings[0]);
        int minute = Integer.parseInt(strings[1]);
        Day day = Day.valueOf(strings[2]);
        
        return new Date(hour, minute, day);
    }

    @Override
    public void load(String filename) throws IOException, 
            Date.BadDateException, Item.BadDatesException, 
            Item.OverlappingDateException
    {
        items.clear();
        
        String jarPath = getJarPath();
        String filePath = filename + jarPath;
        
        FileReader fr = new FileReader(filename);
        CSVReader csvr = new CSVReader(fr);
        
        String[] entries;
        while ((entries = csvr.readNext()) != null)
        {
            Date start = getDateFromStrings(Arrays.copyOfRange(entries, 0, 3));
            Date end = getDateFromStrings(Arrays.copyOfRange(entries, 3, 6));
            String name = entries[6];
            String leading = entries[7];
            String room = entries[8];
            String faculty = entries[9];
            
            Item item = new Item(start, end, name, leading, faculty, room);
            
            addItem(item);
        }
        
        csvr.close();
    }

    private String getJarPath()
    {
        return ClassLoader.getSystemClassLoader().getResource(".").getPath();
    }
}

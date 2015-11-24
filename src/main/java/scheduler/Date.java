package scheduler;

/**
 *
 * @author robert
 */
public class Date
{
    // liczba minut od rozpoczęcia tygodnia, gdzie 0 to poniedziałek o północy
    private final int minutesFromWeekStart;
    
    private final int hour;
    private final int minute;
    private final Day day;

    public Date(int hour, int minute, Day day) throws BadDateException
    {
        if (!validateHour(hour) || !validateMinute(minute))
        {
            throw new BadDateException();
        }
        
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        
        this.minutesFromWeekStart = (day.ordinal() * 24 * 60) + 
                (hour * 60) + minute;
    }
    
    private boolean validateHour(int hour)
    {
        return (hour <= 23 && hour >= 0);
    }
    
    private boolean validateMinute(int minute)
    {
        return (minute <= 59 && minute >= 0);
    }
    
    public boolean isBefore(Date anotherDate)
    {
        return this.minutesFromWeekStart < anotherDate.minutesFromWeekStart;
    }
    
    public boolean isAfter(Date anotherDate)
    {
        return this.minutesFromWeekStart > anotherDate.minutesFromWeekStart;
    }
    
    public boolean isBeforeOrEqual(Date anotherDate)
    {
        return this.minutesFromWeekStart <= anotherDate.minutesFromWeekStart;
    }
    
    public boolean isAfterOrEqual(Date anotherDate)
    {
        return this.minutesFromWeekStart >= anotherDate.minutesFromWeekStart;
    }
    
    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public Day getDay()
    {
        return day;
    }
    
    public static class BadDateException extends Exception
    {
        public BadDateException()
        {
        }
    }
}

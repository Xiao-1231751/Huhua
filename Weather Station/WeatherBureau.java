// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Huhua Xiao (xiaohuhua2019)
import java.util.*;
//-------------------------------------------------------------------------
/**
 *  A weather service that keeps track of all the weather stations
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Huhua Xiao (xiaohuhua2019)
 *  @version 2021/11/18
 */
public class WeatherBureau
{
    //~ Fields ................................................................
    private Map<String, WeatherStation> newMap;
    private WeatherStation xiao;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherBureau object.
     */
    public WeatherBureau()
    {
        super();
        newMap = new HashMap<String, WeatherStation>();
        /*# Do any work to initialize your class here. */
    }

    //~ Methods ...............................................................
    /**
     * The method to read the text, and get the month, rainfall, and name
     * of the WeatherStation
     * @param text A long sentence that contains the information
     * of the WeatherStation
     */
    public void recordDailySummary(String text)
    {
        Scanner input = new Scanner(text);
        String newWord = "";
        String monthNumber = "";
        String a = "";
        String name = input.next();
        xiao = new WeatherStation(name);
        for (int i = 0; i < 6; i++)
        {
            a = input.next();
            if (i == 3)
            {
                int index = a.indexOf("/");
                monthNumber = a.substring(0, index);
            }
            if (i == 4)
            {
                newWord = a; 
            } 
        }
        int str1 = Integer.parseInt(monthNumber);
        double str2 = Double.parseDouble(newWord);
        if (!newWord.equals("-1"))
        {
            for (Map.Entry<String, WeatherStation> pair : newMap.entrySet())
            {
                if (name.equals(pair.getKey()))
                {
                    pair.getValue().recordDailyRain(str1, str2);
                }
            }
            if (!newMap.containsKey(name))
            {
                xiao.recordDailyRain(str1, str2);
                newMap.put(name, xiao);
            }
        }
    }
    
    /**
     * The method to read the next line, and also run 
     * recordDailySummary(nextLine)
     * @param input The scanner to read the nextLine
     */
    public void recordDailySummaries(Scanner input)
    {
        while (input.hasNext())
        {
            String nextLine = input.nextLine();
            this.recordDailySummary(nextLine);
        }
    }

    /**
     * The method to use the identifier(key) to find the value in map,
     * and return null if there is not key in map
     * @param identifier The key in the map, and also the ID 
     * @return newMap.get(identifier)
     */
    public WeatherStation getStation(String identifier)
    {
        //return newMap.get(identifier);
        for (Map.Entry<String, WeatherStation> pair : newMap.entrySet())
        {
            if ((pair.getKey()).equals(identifier))
            {
                return pair.getValue();
            }
        }
        return null;
    }
    
    /**
     * The method to get the WeatherStation of the lowest average rainfall
     * in a specific month
     * @param month The month number between 1 to 12 
     * @return xiao;
     */
    public WeatherStation lowestStation(int month)
    {
        double result = 10000000000000.0;
        for (Map.Entry<String, WeatherStation> pair : newMap.entrySet())
        {
            if (pair.getValue().getAvgForMonth(month) < result
                && pair.getValue().getAvgForMonth(month) != -1)
            {
                xiao = pair.getValue();
                result = xiao.getAvgForMonth(month);
            }
        }
        return xiao;
    }
    
    /**
     * The method to get the WeatherStation of the lowest average rainfall 
     * in all months
     * @return xiao
     */
    public WeatherStation lowestStation()
    {
        double answer = 10000000000000.0;
        for (Map.Entry<String, WeatherStation> pair : newMap.entrySet())
        {
            int result = pair.getValue().getLowestMonth();
            if (pair.getValue().getAvgForMonth(result) < answer)
            {
                xiao = pair.getValue();
                answer = xiao.getAvgForMonth(result);
            }
        }
        return xiao;

    }
}

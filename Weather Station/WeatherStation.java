// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Huhua Xiao (xiaohuhua2019)

//-------------------------------------------------------------------------
/**
 *  The calss represents the basic statistics collected by one weather 
 *  observation station
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Huhua Xiao (xiaohuhua2019)
 *  @version 2021/11/17
 */
public class WeatherStation
{
    //~ Fields ................................................................
    private String name;
    private double[] array;
    private int[] times;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherStation object.
     * @param identifier The name of the station
     */
    public WeatherStation(String identifier)
    {
        super();
        this.name = identifier;
        array = new double[12];
        times = new int[12];
        /*# Do any work to initialize your class here. */
    }
    
    //~ Methods ...............................................................
    /**
     * The method to return the weather station ID for the weatherstation
     * @return name
     */
    public String getId()
    {
        return this.name;
    }
    
    /**
     * The method add the times and numbers of rainfall in the 
     * correct array
     * @param month The month of raining
     * @param rainfall The times of raining 
     */
    public void recordDailyRain(int month, double rainfall)
    {
        if (month > 0 && month < 13)
        {
            array[month - 1] += rainfall;
            times[month - 1] += 1;
        }
    }
    
    /**
     * The method to return return the rainfall times in a specific month
     * @param month The number of the month between 1 to 12
     * @return times[month - 1];
     */
    public int getCountForMonth(int month)
    {
        if (month > 0 && month < 13)
        {
            return times[month - 1];
        }
        return -1;
    }
    
    /**
     * The method to get the average rainfall in a specific month
     * @param month The number of the month between 1 to 12
     * @return c
     */
    public double getAvgForMonth(int month)
    {
        int a = times[month - 1];
        double b = array[month - 1];
        double c = -1;
        if (a != 0)
        {
            c = b / a;
        }
        return c;
    }
    
    /**
     * The method to get the month number of the lowest 
     * average rainfall in the all months
     * @return number
     */
    public int getLowestMonth()
    {
        int number = 0;
        double answer = 1000.0;
        for (int i = 0; i < array.length; i++)
        {
            if (getAvgForMonth(i + 1) < answer 
                && getAvgForMonth(i + 1) != -1)
            {
                answer = this.getAvgForMonth(i + 1);
                number = i;
            }
        }
        return number + 1;
    }
}

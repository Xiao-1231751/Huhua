// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Huhua Xiao (xiaohuhua2019)
import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 *  The class to test the methods of WeatherStation
 *  Summarize what your test objectives are.
 *
 *  @author Huhua Xiao (xiaohuhua2019)
 *  @version 2021/11/18
 */
public class WeatherStationTest
    extends TestCase
{
    //~ Fields ................................................................
    private WeatherStation xiao;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherStationTest test object.
     */
    public WeatherStationTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        xiao = new WeatherStation("Guo");
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Test if this method can get the name of the station
     */
    public void testGetId()
    {
        assertThat(xiao.getId()).isEqualTo("Guo");
    }
    
    /**
     * Test if this method can record the correct data 
     * in the correct array
     */
    public void testRecordDailyRain()
    {
        xiao.recordDailyRain(2, 1.0);
        assertThat(xiao.getCountForMonth(2)).isEqualTo(1);
        assertThat(xiao.getAvgForMonth(2)).isEqualTo(1.0, within(0.001));
    }
    
    /**
     * Test if this method can record the correct data 
     * in the correct array
     */
    public void testRecordDailyRainSecond()
    {
        xiao.recordDailyRain(-4, 1.0);
        assertThat(xiao.getCountForMonth(2)).isEqualTo(0);
        assertThat(xiao.getAvgForMonth(2)).isEqualTo(-1, within(0.001));
    }
    
    /**
     * Test if this method can record the correct data 
     * in the correct array
     */
    public void testRecordDailyRainAgain()
    {
        xiao.recordDailyRain(15, 1.0);
        assertThat(xiao.getCountForMonth(2)).isEqualTo(0);
        assertThat(xiao.getAvgForMonth(2)).isEqualTo(-1, within(0.001));
    }
    
    /**
     * Test if this method can get the value of 
     * average rainfall correctly.
     */
    public void testGetAvgForMonth()
    {
        assertThat(xiao.getAvgForMonth(2)).isEqualTo(-1.0, within(0.001));
    }
    
    /**
     * Test if this method can get the value of 
     * average rainfall correctly.
     */
    public void testGetCountMonth()
    {
        int a = xiao.getCountForMonth(20);
        assertThat(a).isEqualTo(-1);
    }
    
    /**
     * Test if this method can get the value of 
     * average rainfall correctly.
     */
    public void testGetCountMonthAgain()
    {
        int a = xiao.getCountForMonth(-10);
        assertThat(a).isEqualTo(-1);
    }
    
    /**
     * Test if this method can get the moneth number that hava the lowest
     * average rainfall
     */
    public void testGetLowestMonth()
    {
        xiao.recordDailyRain(1, 2.0);
        xiao.recordDailyRain(2, 2.0);
        xiao.recordDailyRain(3, 2.0);
        xiao.recordDailyRain(4, -1);
        xiao.recordDailyRain(5, 2.0);
        xiao.recordDailyRain(6, 2.0);
        xiao.recordDailyRain(7, 2.0);
        xiao.recordDailyRain(8, 2.0);
        xiao.recordDailyRain(9, 2.0);
        xiao.recordDailyRain(10, 2.0);
        xiao.recordDailyRain(11, 1.0);
        xiao.recordDailyRain(12, 2.0);
        assertThat(xiao.getLowestMonth()).isEqualTo(11);
    }
}

import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.util.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Huhua Xiao (xiaohuhua2019)

// -------------------------------------------------------------------------
/**
 *  The class to test the methods of WeatherBureau
 *  Summarize what your test objectives are.
 *
 *  @author Huhua Xiao (xiaohuhua2019)
 *  @version 2021/11/18
 */
public class WeatherBureauTest
    extends TestCase
{
    //~ Fields ................................................................
    private WeatherBureau bureau;
    private Map<String, WeatherStation> newMap;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherBureauTest test object.
     */
    public WeatherBureauTest()
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
        bureau = new WeatherBureau();
        newMap = new HashMap<String, WeatherStation>();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Test if recordDailySummary work or not, and if it add
     * the correct data in the array
     */
    public void testRecordDailySummary()
    {
        String line = "KE000063612 3.117 35.617 515 2/10/16 0.04 87 98 73";
        bureau.recordDailySummary(line);
        // place your assertions here
        assertThat(bureau.getStation("KE000063612")
            .getCountForMonth(2)).isEqualTo(1);
        assertThat(bureau.getStation("KE000063612")
            .getAvgForMonth(2)).isEqualTo(0.04);
    }
    
    /**
     * Test the recordDailySummaryAgain in the situation that the 
     * map didn't contain the ID name of the text
     */
    public void testRecordDailySummaryAgain()
    {
        String line = "KE000063612 3.117 35.617 515 2/10/16 0.04 87 98 73";
        String nextLine 
            = "KE000063612 3.117 35.617 515 2/10/16 0.05 87 98 73";
        bureau.recordDailySummary(line);
        bureau.recordDailySummary(nextLine);
        // place your assertions here
        assertThat(bureau.getStation("KE000063612")
            .getCountForMonth(2)).isEqualTo(2);
        assertThat(bureau.getStation("KE000063612")
            .getAvgForMonth(2)).isEqualTo(0.045);
    }
    
    /**
     * Test if recorddailySummaries work or not, and test if this method
     * will put the correct data in the correct array, and 
     * if the scanner can read the next line
     */
    public void testRecordDailySummaries()
    {
        setIn(
            "KE000063612 3.117 35.617 515 2/10/16 0.04 87 98 73",
            "KE000063820 -4.033 39.617 55 10/25/16 0.1 88 101 75",
            "KE000063822 -4.033 39.617 55 4/25/16 -1 88 101 75"
            // put as many lines as you want, each as a separate string
        );
        bureau.recordDailySummaries(in());
        // place your assertions here
        assertThat(bureau.getStation("KE000063612")
            .getCountForMonth(2)).isEqualTo(1);
        assertThat(bureau.getStation("KE000063612")
            .getAvgForMonth(2)).isEqualTo(0.04);
        assertThat(bureau.getStation("KE000063820")
            .getCountForMonth(10)).isEqualTo(1);
        assertThat(bureau.getStation("KE000063820")
            .getAvgForMonth(10)).isEqualTo(0.1);
    }
    
    /**
     * Test if getStation work or not in the situation that will return
     * null
     */
    public void testGetStation()
    {
        WeatherStation xiao = new WeatherStation("KE000063612");
        newMap.put("KE000063612", xiao);
        String a = "KE000063612";
        assertThat(bureau.getStation(a)).isEqualTo(null);
    }
    
    /**
     * Test if this methond can return the WeatherStation that has 
     * the lowest average rainfall in a specific month
     */
    public void testLowestStation()
    {
        String line = "KE000063612 3.117 35.617 515 2/10/16 0.04 87 98 73";
        
            
        bureau.recordDailySummary(line);
        
        
        assertThat(bureau.lowestStation(2))
            .isEqualTo(bureau.getStation("KE000063612"));
    }
    
    
    /**
     * Test if this methond can return the WeatherStation that has 
     * the lowest average rainfall in the all months
     */
    public void testLowestStationAgain()
    {
        String line = "KE000063612 3.117 35.617 515 4/10/16 0.04 87 98 73";
        
        bureau.recordDailySummary(line);
        
        assertThat(bureau.lowestStation())
            .isEqualTo(bureau.getStation("KE000063612"));
    }
    
    /**
     * Test if this methond can return the WeatherStation that has 
     * the lowest average rainfall in the all months
     */
    public void testLowestStationOneMoreTime()
    {
        String line = "KE000063612 3.117 35.617 515 4/10/16 0.00 87 98 73";
        
        bureau.recordDailySummary(line);
        
        assertThat(bureau.lowestStation())
            .isEqualTo(bureau.getStation("KE000063612"));
    }
    
    
    
    /**
     * Test the situation that the average rainfall is higher than 
     * the predict number (double answer = 10000000000000.0;) in a 
     * specific month
     */
    public void testLowestStationFourth()
    {
        String line 
            = "KE000063612 3.117 35.617 515 2/10/16 10000000000000.0 87 98 73";
        
            
        bureau.recordDailySummary(line);
        
        
        assertThat(bureau.lowestStation(2))
            .isEqualTo(bureau.getStation("KE000063612"));
    }
    
    /**
     * Test the situation that the average rainfall is higher than 
     * the predict number (double answer = 10000000000000.0;) (Test
     * this situation again)
     */
    public void testLowestStationFifth()
    {
        String line 
            = "KE000063612 3.117 35.617 515 2/10/16 100000000000000.0 87 98 73";
        String newLine 
            = "KE000063613 3.117 35.617 515 3/10/16 100000000000000.0 87 98 73";
        bureau.recordDailySummary(line);
        bureau.recordDailySummary(newLine);
        
        assertThat(bureau.lowestStation())
            .isEqualTo(bureau.getStation("KE000063613"));
    }
}

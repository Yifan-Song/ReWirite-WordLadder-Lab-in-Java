package Wordladder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import Wordladder.App.*;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAppToStandardString()
    {
        assertTrue( App.ToStandardString("Apple").equals("apple") );
        assertTrue( App.ToStandardString("orangE").equals("orange") );
        assertTrue( App.ToStandardString("SUN").equals("sun") );
        assertTrue( App.ToStandardString("ENTER").equals("enter") );
        assertTrue( App.ToStandardString("lo ve").equals("love") );
        assertTrue( App.ToStandardString("Hom e").equals("home") );
        assertTrue( App.ToStandardString("h u r T").equals("hurt") );
    }

    public void testAppIsWord()
    {
        Set<String> testSet = new HashSet<String>();
        testSet.add("apple");
        testSet.add("orange");
        testSet.add("sun");
        assertTrue( App.IsWord("apple",testSet) );
        assertTrue( App.IsWord("orange",testSet) );
        assertTrue( App.IsWord("sun",testSet) );
        assertFalse( App.IsWord("abc",testSet) );
        assertFalse( App.IsWord("123456",testSet) );
        assertFalse( App.IsWord("&*^%&",testSet) );
        assertFalse( App.IsWord("app%le",testSet) );
        assertFalse( App.IsWord("A pp le",testSet) );
    }
    public void testSearch()
    {
        Set<String> testSet = new HashSet<String>();
        testSet.add("tea");
        testSet.add("ten");
        testSet.add("teen");
        assertTrue( App.Search("tea","ten",testSet) );
        assertTrue( App.Search("ten","teen",testSet) );
        assertTrue( App.Search("tea","teen",testSet) );
        assertFalse( App.Search("tea","SE",testSet) );
        assertFalse( App.Search("teen","boy",testSet) );
    }
}
//the main function and the actual output is hard to write unit test
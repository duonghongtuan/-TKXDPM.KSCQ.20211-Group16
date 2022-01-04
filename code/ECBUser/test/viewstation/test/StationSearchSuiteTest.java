package viewstation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import viewbike.test.BikeSearchBlackBoxTest;
import viewbike.test.BikeSearchWhiteBoxTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({StationSearchBlackBoxTest.class, StationSearchWhiteBoxTest.class})
public class StationSearchSuiteTest {

}

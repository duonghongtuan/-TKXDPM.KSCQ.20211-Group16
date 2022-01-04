package viewstation.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

public class StationSearchBlackBoxTest extends ParameterizedStationSearchTest{
	public StationSearchBlackBoxTest(String param, Object expectedResult) {
		super(param, expectedResult);
	}
	
	
	@Parameterized.Parameters
	public static Collection<Object[]> allTestCase(){
		return Arrays.asList(new Object[][] {
			{"bach khoa", 2},
			{"12345678", 2},
			{"giáp bát", 1},
			{"station 4",  1},
			{"station 20", 	0},
		});
	}
}

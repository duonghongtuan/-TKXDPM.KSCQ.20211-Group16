package viewstation.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

public class StationSearchWhiteBoxTest extends ParameterizedStationSearchTest{
	public StationSearchWhiteBoxTest(String param, Object expectedResult) {
		super(param, expectedResult);
	}
	
	
	@Parameterized.Parameters
	public static Collection<Object[]> allTestCase(){
		return Arrays.asList(new Object[][] {
			{"bách khoa", 2},
			{"station 3",  1},
			{null, 	4},
		});
	}
}

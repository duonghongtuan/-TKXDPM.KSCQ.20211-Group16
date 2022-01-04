package viewbike.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

public class BikeSearchWhiteBoxTest extends ParameterizedBikeSearchTest{
	
	public BikeSearchWhiteBoxTest(String stationId, String param, Object expectedResult) {
		super(stationId, param, expectedResult);
		// TODO Auto-generated constructor stub
	}

	@Parameterized.Parameters
	public static Collection<Object[]> allTestCase(){
		return Arrays.asList(new Object[][] {
			{"station1", "Xe đạp đơn thường (Bike)",     1},
			{"station1", "Tất Cả", 						 6},
			{"station1", null, 							 6},
		});
	}
}

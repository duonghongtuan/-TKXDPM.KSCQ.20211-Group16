package viewbike.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


public class BikeSearchBlackBoxTest extends ParameterizedBikeSearchTest{

	public BikeSearchBlackBoxTest(String stationId, String param, Object expectedResult) {
		super(stationId, param, expectedResult);
	}
	
	
	@Parameterized.Parameters
	public static Collection<Object[]> allTestCase(){
		return Arrays.asList(new Object[][] {
			{"station1", "Xe đạp đơn thường (Bike)",     1},
			{"station1", "Xe đạp đơn điện (EBike)",      4},
			{"station1", "Xe đạp đôi thường (TWinBike)", 1},
			{"station1", "Tất Cả", 						 6},
			{"station1", null, 							 6},
		});
	}
	

	
	
	
}

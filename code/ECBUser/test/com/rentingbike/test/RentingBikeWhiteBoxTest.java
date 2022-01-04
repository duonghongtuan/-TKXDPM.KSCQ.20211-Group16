package com.rentingbike.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ecb.bean.Bike;

import rentingbike.RentingBikeController;

@RunWith(Parameterized.class)
public class RentingBikeWhiteBoxTest {
	private String bikeCode;
	private Bike expectedResult;
	private RentingBikeController controller = new RentingBikeController();
	
	public RentingBikeWhiteBoxTest(String bikeCode,Bike expectedResult) {
		this.bikeCode = bikeCode;
		this.expectedResult = expectedResult;
	}
	
	

	@Parameterized.Parameters
	public static Collection<Object[]> testCases() {
		Bike bike = new Bike("bike2","kfjaklsf",15,"99N2-6969",new Date(2020,11,9),"ASAMA",700000,1,"station1",false);
		return Arrays.asList(new Object[][] { 
			{"bikebike",null},{"bike1",null},{"bike2",bike}
		});
	}
	
	@Test
	public void testTotalCost() {
		Bike res = new Bike();
		res = controller.getBikeByCode(bikeCode);
		System.out.println(expectedResult);
		if (res!=null) {
			assertTrue("Error in get tolal cost",res.equals(expectedResult) );
		}else {
			assertTrue("Error in get tolal cost",res==expectedResult);
		}
		
	}
}

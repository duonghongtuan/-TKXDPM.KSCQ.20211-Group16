package viewbike.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;

import viewbike.UserBikePageController;

@RunWith(Parameterized.class)
public class ParameterizedBikeSearchTest {
	String stationId;
	Map<String, String> params;
	Object expectedResult;
	List<DockingStation> dockingStations;
	public ParameterizedBikeSearchTest(String stationId, String param, Object expectedResult) {
		this.stationId = stationId;
		if(param != null) {
			this.params = new HashMap<String, String>();
			this.params.put("type", param);
		}
		this.expectedResult = expectedResult;
	}
	
	@Before
	public void init() {
		dockingStations = new ArrayList<DockingStation>();
		
		DockingStation station1 = new DockingStation("station1", "BX bach khoa", "", 100, 100, 1234);
		ArrayList<Bike> bikes = new ArrayList<Bike>();
		bikes.add(new Bike("bike1", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 0, "station1", true));
		bikes.add(new Bike("bike2", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 1, "station1", true));
		bikes.add(new Bike("bike3", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 1, "station1", true));
		bikes.add(new Bike("bike4", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 1, "station1", true));
		bikes.add(new Bike("bike5", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 1, "station1", true));
		bikes.add(new Bike("bike6", "bikeName", 10, "lisencePlate", null, "manuafaturer", 100, 2, "station1", true));

		
		station1.setListBike(bikes);
		dockingStations.add(station1);		
	}
	
	@Test
	public void testSearch() {
		DockingStation station = null;
		for(DockingStation s: dockingStations) {
			if(stationId.equals(s.getStationId())){
				station = s;
				break;
			}
		}
		assertTrue("Not exist station: "+stationId, station!=null);
		List<? extends Bike> list = UserBikePageController.searchOnList(params, station.getListBike());
		assertEquals("Error in search bike", this.expectedResult, Integer.valueOf(list.size()));
	}
	
	
	
	
}

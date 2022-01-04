package viewstation.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ecb.bean.DockingStation;
import viewstation.UserStationPageController;

@RunWith(Parameterized.class)
public class ParameterizedStationSearchTest {

	Map<String, String> params;
	Object expectedResult;
	List<DockingStation> dockingStations;
	public ParameterizedStationSearchTest(String param, Object expectedResult) {
		if(param != null) {
			this.params = new HashMap<String, String>();
			this.params.put("keyword", param);
		}
		this.expectedResult = expectedResult;
	}
	
	@Before
	public void init() {
		dockingStations = new ArrayList<DockingStation>();
		
		DockingStation station1 = new DockingStation("station1", "BX bách khoa", "", 100, 100, 12345678);
		DockingStation station2 = new DockingStation("station2", "BX giáp bát", "", 100, 100, 987654321);
		DockingStation station3 = new DockingStation("station3", "BX KTX bách khoa", "", 100, 100, 12345678);
		DockingStation station4 = new DockingStation("station4", "BX thống nhất", "", 100, 100, 78854689);
		
		dockingStations.add(station1);
		dockingStations.add(station2);
		dockingStations.add(station3);
		dockingStations.add(station4);
		
		
	}
	
	@Test
	public void testSearch() {
		List<? extends DockingStation> list = UserStationPageController.searchOnList(params, dockingStations);
		assertEquals("Error in search station", this.expectedResult, Integer.valueOf(list.size()));
	}
}

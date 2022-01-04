package viewstation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ecb.bean.DockingStation;

import abstractdata.controller.ADataPageController;
import abstractdata.gui.ADataListPane;
import abstractdata.gui.ADataPagePane;
import abstractdata.gui.ADataSearchPane;
import abstractdata.gui.ADataSinglePane;
import abstractdata.gui.AHeaderPane;
import api.EcoBikeApi;
import api.EcoBikeApiFactory;
import main.ECBUserController;
import other.StringStyle;
import other.searchutil.ISearcher;
import other.searchutil.SearchImpl;
import viewbike.UserBikePageController;

public class UserStationPageController extends ADataPageController<DockingStation>{
	
	ECBUserController ecbUserController;


	public UserStationPageController() {
		// TODO Auto-generated constructor stub
	}
	
	public UserStationPageController(ECBUserController ecbUserController){
		this.ecbUserController = ecbUserController;
	}
	
	
	@Override
	public ADataSearchPane createSearchPane() {
		// TODO Auto-generated method stub
		return new StationSearchPane();
	}
	
	
	public void goPage(JPanel panel) {
		ecbUserController.goPage(panel);
	}
	

	@Override
	public List<? extends DockingStation> search(Map<String, String> params) {
		// TODO Auto-generated method stub
		
		List<DockingStation> stations = EcoBikeApiFactory.getInstance().getStation(new HashMap<String, String>());
		return searchOnList(params, stations);
	}
	

	public static List<? extends DockingStation> searchOnList(Map<String, String> params, List<DockingStation> stations) {
		// TODO Auto-generated method stub
		if(params == null) {
			return stations;
		}else {
			String keyword = params.get("keyword");
			ISearcher searcher = new SearchImpl();
			List<DockingStation> resultList = new ArrayList<DockingStation>();
			Iterator<DockingStation> iter = stations.iterator();
			DockingStation station;
			while(iter.hasNext()) {
				station = iter.next();
				if(searcher.isMatched(station, keyword)) {
					resultList.add(station);
				}
			}
			return resultList;
		}
	}

	@Override
	public ADataSinglePane<DockingStation> createSinglePane() {
		// TODO Auto-generated method stub
		return new StationSinglePane();
	}

	@Override
	public ADataListPane<DockingStation> createListPane() {
		// TODO Auto-generated method stub
		return new StationListPane(this);
	}
	
	private AHeaderPane getHeaderPane() {
		AHeaderPane header = new AHeaderPane() {
		};
		JLabel label = new JLabel();
		label.setText("Danh sách bãi xe");
		label.setFont(StringStyle.BIG_FONT);
		header.add(label);
		return header;
	}
	
	public void onViewStationDetail(ADataSinglePane<DockingStation> singlePane) {
		JPanel panel = new UserBikePageController(singlePane.getData()).getDataPagePane();
		goPage(panel);
	}
	
	
	@Override
	public ADataPagePane<DockingStation> getDataPagePane() {
		// TODO Auto-generated method stub
		super.getDataPagePane().setHeader(getHeaderPane());
		return super.getDataPagePane();
	}
	
	

}

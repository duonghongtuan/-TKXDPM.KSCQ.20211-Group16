package viewbike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;

import abstractdata.controller.ADataPageController;
import abstractdata.controller.IDataSearchController;
import abstractdata.gui.ADataListPane;
import abstractdata.gui.ADataPagePane;
import abstractdata.gui.ADataSearchPane;
import abstractdata.gui.ADataSinglePane;
import api.EcoBikeApi;
import api.EcoBikeApiFactory;
import other.Constants;
import rentingbike.RentingBikeController;

public class UserBikePageController extends ADataPageController<Bike>{
	
	private DockingStation station;

	
	public UserBikePageController(String stationId) {
		super(null);
		Map<String, String> x = new HashMap<String, String>();
		x.put("id", stationId);
		this.station = EcoBikeApiFactory.getInstance().getStation(x).get(0);
		createGUI();
	}
	
	public UserBikePageController(DockingStation station) {
		// TODO Auto-generated constructor stub
		super(null);
		this.station = station;
		this.station.setListBike(EcoBikeApiFactory.getInstance().getBikes(station.getStationId()));
		this.createGUI();
	}
	
	@Override
	public void createGUI() {
		// TODO Auto-generated method stub
		super.createGUI();
	}


	@Override
	public ADataSearchPane createSearchPane() {
		// TODO Auto-generated method stub
		return new BikeSearchPane();
	}

	@Override
	public List<? extends Bike> search(Map<String, String> params) {
		// TODO Auto-generated method stub
		List<Bike> bikes = EcoBikeApiFactory.getInstance().getBikes(this.station.getStationId());
		return searchOnList(params, bikes);
	}
	

	public static List<? extends Bike> searchOnList(Map<String, String> params, List<Bike> bikes) {
		// TODO Auto-generated method stub
		if(params == null)
			return bikes;
		else {
			String type = params.get("type");
			if(type.equals(Constants.ALL)) 
				return bikes;
			Iterator<Bike> iter = bikes.iterator();
			List<Bike> resultList = new ArrayList<Bike>();
			Bike temp; 
			while(iter.hasNext()) {
				temp = iter.next();
				if(temp.getType() == Constants.BIKE.getBikeTypeId(type)) {
					resultList.add(temp);
				}
			}
			return resultList;
		}
	}
	
	public void onRent(ADataSinglePane<Bike> singlePane) {
		Bike bike = (Bike)singlePane.getData();
		RentingBikeController rentingBikeController = new RentingBikeController(bike);
		
		rentingBikeController.setFinishedEventCallback(new Runnable() {
			@Override
			public void run() {
				singlePane.updateData(bike);
			}
		});
	}

	@Override
	public ADataSinglePane<Bike> createSinglePane() {
		// TODO Auto-generated method stub
		return new BikeSinglePane();
	}

	@Override
	public ADataListPane<Bike> createListPane() {
		// TODO Auto-generated method stub
		return new BikeListPane(this);
	}
	

	@Override
	public ADataPagePane<Bike> getDataPagePane() {
		// TODO Auto-generated method stub
		super.getDataPagePane().setHeader(new BikePageHeader(station));
		return super.getDataPagePane();
	}

}

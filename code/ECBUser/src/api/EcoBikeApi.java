package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;
import com.ecb.bean.CreditCard;
import com.ecb.bean.Customer;
public interface EcoBikeApi {
	public static final String PATH = "http://localhost:8080/";
	
	public static EcoBikeApi getInstance() {
		return new EcobikeApiImpl();
	}
	
	public ArrayList<DockingStation> getAllStation() ;
	public ArrayList<DockingStation> getStation(Map<String, String> queryParams);
	public ArrayList<DockingStation> getStationById(Map<String, String> queryParams) ;
	public DockingStation updateDockingStation(DockingStation station);
	public DockingStation getDockingStation(String id);
	
	public ArrayList<Bike> getBikes(String stationId);
	public Bike getBikeByCode(String BikeId);
	public Bike updateBike(Bike bike);	
	public Bike addBike(Bike bike) ;
	
    public Customer getCustomer(String customerId);
    
	public Order updateOrder(Order order);
	public Order deleteOrder(Order order) ;
	
	public Order addOrder(Order order);
	public ArrayList<Order> getOrder();
	
	public double plusAmount(String cardId, double money) ;
	public CreditCard getCard(String cardId) ;
	public double minusAmount(String cardId, double money);
	
	public static String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

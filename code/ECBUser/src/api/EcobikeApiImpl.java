
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

import api.bank.BankApiImpl;
import api.bank.IBankApi;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;
import com.ecb.bean.CreditCard;
import com.ecb.bean.Customer;

public class EcobikeApiImpl implements EcoBikeApi {
	public static final String PATH = "http://localhost:8080/";
	
	private Client client;
	protected IBankApi bankAPI;
	
	EcobikeApiImpl() {
		client = ClientBuilder.newClient();
		bankAPI= new BankApiImpl();
	}
	
	public ArrayList<DockingStation> getAllStation() {
		WebTarget webTarget = client.target(PATH).path("stations");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<DockingStation> res = response.readEntity(new GenericType<ArrayList<DockingStation>>() {});
		System.out.println(res);
		return res;
	}
	
	public ArrayList<DockingStation> getStation(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(PATH).path("stations");
		
		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<DockingStation> res = response.readEntity(new GenericType<ArrayList<DockingStation>>() {});
		System.out.println(res);
		return res;
	}
	
	public ArrayList<DockingStation> getStationById(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(PATH).path("stations");
		
		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<DockingStation> res = response.readEntity(new GenericType<ArrayList<DockingStation>>() {});
		System.out.println(res);
		return res;
	}
	
	public ArrayList<Bike> getBikes(String stationId) {
		WebTarget webTarget = client.target(PATH).path("bikes");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {});
		ArrayList<Bike> rs = new ArrayList<Bike>();
		for (int i=0;i<res.size();i++) {
			if (res.get(i).getStationId().equals(stationId)) {
				rs.add(res.get(i));
			}
		}
		System.out.println(res);
		return rs;
	}
	
	public Bike getBikeByCode(String BikeId) {
		WebTarget webTarget = client.target(PATH).path("bikes");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {});
		ArrayList<Bike> rs = new ArrayList<Bike>();
		for (int i=0;i<res.size();i++) {
			Bike bike = res.get(i);
			String id = bike.getBikeId();
			if (id.equals(BikeId)) return bike;
		}
		return null;
	}

	
    public Customer getCustomer(String customerId) {
    	WebTarget webTarget = client.target(PATH).path("customers");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Customer> res = response.readEntity(new GenericType<ArrayList<Customer>>() {});
		for (int i=0;i<res.size();i++) {
			if (res.get(i).getCustomerId().equals(customerId)) {
				return res.get(i);
			}
		}
	    return null;
    }

	public Order updateOrder(Order order) {
		WebTarget webTarget = client.target(PATH).path("orders").path(order.getOrderId());
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));
		
		Order res = response.readEntity(Order.class);
		return res;
	}
	
	public Order deleteOrder(Order order) {
        WebTarget webTarget = client.target(PATH).path("orders").path("delete").path(order.getOrderId());
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));
		
		Order res = response.readEntity(Order.class);
		return res;
	}
	
	public Bike updateBike(Bike bike) {
		WebTarget webTarget = client.target(PATH).path("bikes").path(bike.getBikeId());
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));
		
		Bike res = response.readEntity(Bike.class);
		return res;
	}

	
	public Bike addBike(Bike bike) {
		WebTarget webTarget = client.target(PATH).path("bikes");
		
		String jsonBike = convertToJson(bike);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.post(Entity.entity(jsonBike, MediaType.APPLICATION_JSON));
		
		System.out.println(response.getStatus());
		
		Bike res = response.readEntity(Bike.class);
		return res;
	}
	
	public Order addOrder(Order order) {
		WebTarget webTarget = client.target(PATH).path("orders");
		
		String jsonOrder = convertToJson(order);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.post(Entity.entity(jsonOrder, MediaType.APPLICATION_JSON));
		
		System.out.println(response.getStatus());
		
		Order res = response.readEntity(Order.class);
		return res;
	}
	
	public ArrayList<Order> getOrder() {
		WebTarget webTarget = client.target(PATH).path("orders");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Order> res = response.readEntity(new GenericType<ArrayList<Order>>() {});
		
		return res;
	}
	
	public DockingStation updateDockingStation(DockingStation station) {
		WebTarget webTarget = client.target(PATH).path("stations").path(station.getStationId());
		String jsonStation= convertToJson(station);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.post(Entity.entity(jsonStation, MediaType.APPLICATION_JSON));
		DockingStation res = response.readEntity(DockingStation.class);
		return res;
	}
	
	public DockingStation getDockingStation(String id) {
		WebTarget webTarget = client.target(PATH).path("stations");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<DockingStation> res = response.readEntity(new GenericType<ArrayList<DockingStation>>() {});
		for (int i=0;i<res.size();i++) {
			if (res.get(i).getStationId().equals(id)) {
				return res.get(i);
			}
		}
		return null;
	}
	
	public double plusAmount(String cardId, double money) {
		return bankAPI.plusAmount(cardId, money);
	}
	public CreditCard getCard(String cardId) {
		return bankAPI.getCard(cardId);
	}
	public double minusAmount(String cardId, double money) {
		return bankAPI.minusAmount(cardId, money);
	}
	
	
	private static String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.db;

import java.util.ArrayList;

import com.bean.Bike;
import com.bean.CreditCard;
import com.bean.Customer;
import com.bean.DockingStation;
import com.bean.Order;
import com.db.seed.Seed;

public class JsonMediaDatabase implements IMediaDatabase{
	private static IMediaDatabase singleton = new JsonMediaDatabase();

	private ArrayList<Bike> bikes = Seed.singleton().getBikes();
	private ArrayList<DockingStation> stations = Seed.singleton().getStations();
	private ArrayList<Order> orders = Seed.singleton().getOrders();
	private ArrayList<CreditCard> cards = Seed.singleton().getCards();
	private ArrayList<Customer> customers = Seed.singleton().getCustomer();
	
	private JsonMediaDatabase() {
	}
	
	public static IMediaDatabase singleton() {
		return singleton;
	}
	
	@Override
	public Bike addBike(Bike bike) {
		for (Bike b: bikes) {
			if (b.equals(bike)) {
				return null;
			}
		}
		
		bikes.add(bike);
		return bike;
	}
	
	@Override
	public ArrayList<Bike> searchBike(Bike bike) {
		ArrayList<Bike> res = new ArrayList<Bike>();
		for (Bike b: getBikes()) {
			if (b.match(bike)) {
				res.add(b);
			}
		}
		return res;
	}
	
	@Override
	public Bike updateBike(Bike bike) {
		for (Bike b: getBikes()) {
			if (b.equals(bike)) {
				getBikes().remove(b);
				getBikes().add(bike);
				return bike;
			}
		}
		return null;
	}

	@Override
	public DockingStation addDockingStation(DockingStation station) {
		for (DockingStation s: stations) {
			if (s.equals(stations)) {
				return null;
			}
		}
		stations.add(station);
		return station;
	}
	
	@Override
	public ArrayList<DockingStation> searchDockingStation(DockingStation station) {
		ArrayList<DockingStation> res = new ArrayList<DockingStation>();
		for (DockingStation s: stations) {
			if (s.match(station)) {
				res.add(s);
			}
		}
		return res;
	}
	
	@Override
	public DockingStation updateDockingStation(DockingStation station) {
		for (DockingStation s: stations) {
			if (s.equals(station)) {
				stations.remove(s);
				stations.add(station);
				return station;
			}
		}
		return null;
	}
@Override 
public void deleteDockingStation(DockingStation station) {
	for (DockingStation s: stations) {
		if (s.equals(station)) {
			stations.remove(s);
		}
	}
};
	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(ArrayList<Bike> bikes) {
		this.bikes = bikes;
	}

	@Override
	public ArrayList<Order> searchOrder(Order order) {
		ArrayList<Order> res = new ArrayList<Order>();
		for (Order o: orders) {
			if (o.search(order)) {
				res.add(o);
			}
		}
		return res;
	}

	@Override
	public boolean checkOutOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order updateOrder(Order order) {
		for (Order o: orders) {
			if (o.equals(order)) {
				orders.remove(o);
				orders.add(order);
				return order;
			}
		}
		return null;
	}

	@Override
	public Order addOrder(Order order) {
		for (Order o: orders) {
			if (o.equals(order)) {
				return null;
			}
		}
		
		orders.add(order);
		return order;
	}

	@Override
	public CreditCard searchCard(String cardId) {
		for (CreditCard cd : cards) {
			if (cd.getCardId().equals(cardId)) {
				return cd;
			}
		}
		return null;
	}

	@Override
	public double plusAmount(String cartId, double money) {
		for (CreditCard cd : cards) {
			if (cd.getCardId().equals(cartId)) {
				return cd.plusAmount(money);
			}
		}
		return -1;
	}

	@Override
	public double minusAmount(String cartId, double money) {
		for (CreditCard cd : cards) {
			if (cd.getCardId().equals(cartId)) {
				return cd.minusAmount(money);
			}
		}
		return -1;
	}

	@Override
	public ArrayList<Customer> searchCustomer(Customer customer) {
		ArrayList<Customer> res = new ArrayList<Customer>();
		for (Customer c: customers) {
			if (c.search(customer)) {
				res.add(c);
			}
		}
		return res;
	}

	@Override
	public Order deleteOrder(Order order) {
		for (Order o: orders) {
			if (o.equals(order)) {
				orders.remove(o);
				return order;
			}
		}
		return null;
	}

}

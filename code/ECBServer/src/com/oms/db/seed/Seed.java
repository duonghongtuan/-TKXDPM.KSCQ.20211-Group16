package com.oms.db.seed;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.bean.Bike;
import com.oms.bean.CreditCard;
import com.oms.bean.Customer;
import com.oms.bean.DockingStation;
import com.oms.bean.Order;

public class Seed {

	private ArrayList<Order> orders;
	private ArrayList<Bike> bikes;
	private ArrayList<DockingStation> stations;
	private ArrayList<CreditCard> cards;
	private ArrayList<Customer> customers;
	
	private static Seed singleton = new Seed();
	
	private Seed() {
		start();
	}
	
	public static Seed singleton() {
		return singleton;
	}
	
	private void start() {
		
		bikes = new ArrayList<Bike>();
		bikes.addAll(generateBikeFromFile(new File(getClass().getResource("./bikes.json").getPath()).toString()));
		
		stations = new ArrayList<DockingStation>();
		stations.addAll(generateStationFromFile(new File(getClass().getResource("./station.json").getPath()).toString()));
		
		cards = new ArrayList<CreditCard>();
		cards.addAll(generateCardFromFile(new File(getClass().getResource("./cards.json").getPath()).toString()));
		
		customers = new ArrayList<Customer>();
		customers.addAll(generateCustomerFromFile(new File(getClass().getResource("./customers.json").getPath()).toString()));
		
		orders = new ArrayList<Order>();
	}
	
	private ArrayList<Bike> generateBikeFromFile(String filePath){
		ArrayList<Bike> res = new ArrayList<Bike>();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = FileReader.read(filePath);
		try {
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			res = mapper.readValue(json, new TypeReference<ArrayList<Bike>>() { });
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid JSON input data from " + filePath);
		}
		
		return res;
	}
	
	private ArrayList<DockingStation> generateStationFromFile(String filePath){
		ArrayList<DockingStation> res = new ArrayList<DockingStation>();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = FileReader.read(filePath);
		try {
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			res = mapper.readValue(json, new TypeReference<ArrayList<DockingStation>>() { });
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid JSON input data from " + filePath);
		}
		
		return res;
	}
	
	private ArrayList<CreditCard> generateCardFromFile(String filePath){
		ArrayList<CreditCard> res = new ArrayList<CreditCard>();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = FileReader.read(filePath);
		try {
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			res = mapper.readValue(json, new TypeReference<ArrayList<CreditCard>>() { });
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid JSON input data from " + filePath);
		}
		
		return res;
	}
	
	private ArrayList<Customer> generateCustomerFromFile(String filePath){
		ArrayList<Customer> res = new ArrayList<Customer>();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = FileReader.read(filePath);
		try {
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			res = mapper.readValue(json, new TypeReference<ArrayList<Customer>>() { });
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid JSON input data from " + filePath);
		}
		
		return res;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public ArrayList<DockingStation> getStations() {
		return stations;
	}

	public static Seed getSingleton() {
		return singleton;
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public ArrayList<CreditCard> getCards() {
		return cards;
	}
	public ArrayList<Customer> getCustomer() {
		return customers;
	}
	public static void main(String[] args) {
		Seed seed = new Seed();
		seed.start();
	}
}

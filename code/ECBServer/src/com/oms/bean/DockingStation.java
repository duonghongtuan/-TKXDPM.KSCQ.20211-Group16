package com.oms.bean;

import java.util.ArrayList;

public class DockingStation {
	
	private String stationId;
	private String name;
	private String address;
	private int freeSpace;
	private int freeBike;
	private String phone;
	private ArrayList<Bike> listBike;
	
	public DockingStation() {
		super();
	}

	public DockingStation(String stationId, String name, String address, int freeSpace, int freeBike,
			String phone) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.address = address;
		this.freeSpace = freeSpace;
		this.freeBike = freeBike;
		this.phone = phone;
		this.listBike = new ArrayList<Bike>();
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}

	public int getFreeBike() {
		return freeBike;
	}

	public void setFreeBike(int freeBike) {
		this.freeBike = freeBike;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Bike> getListBike() {
		return listBike;
	}

	public void setListBike(ArrayList<Bike> listBike) {
		this.listBike = listBike;
	}
	
	public boolean match(DockingStation dockingStation) {
		if (dockingStation == null)
			return true;
		
		if (dockingStation.stationId != null && !dockingStation.stationId.equals("") && !this.stationId.contains(dockingStation.stationId)) {
			return false;
		}
		if (dockingStation.name != null && !dockingStation.name.equals("") && !this.name.contains(dockingStation.name)) {
			return false;
		}
		if (dockingStation.address != null && !dockingStation.address.equals("") && !this.address.contains(dockingStation.address)) {
			return false;
		}
		if (dockingStation.freeBike != 0 && this.freeBike != dockingStation.freeBike) {
			return false;
		}
		if (dockingStation.freeSpace != 0 && this.freeSpace != dockingStation.freeSpace) {
			return false;
		}
		if (!dockingStation.phone.equals("") && this.phone.equals(dockingStation.phone)) {
			return false;
		}
		return true;
	}
	public boolean equals(DockingStation obj) {
		return this.stationId.equals(obj.stationId);
	}

}

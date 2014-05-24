package in.ultraneo.farecalculator9;

import android.location.Address;

public class ResultSet {
	private String head;
	private String subhead;
	private String location;
	private double latitude;
	private double longitude;
	private Address address;
	public String getHead() {
		return head;
	}

	public String getSubhead() {
		return subhead;
	}

	public String getLocation() {
		return location;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setResultSet(String head, String subhead, String location,
			double latitude, double longitude, Address address) {
		this.head = head;
		this.subhead = subhead;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	
}

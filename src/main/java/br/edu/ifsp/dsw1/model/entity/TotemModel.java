package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

public abstract class TotemModel implements FlightDataObserver{
	
	private List<FlightData> datasource;
	
	public TotemModel()
	{
		datasource = new ArrayList<FlightData>();
	}
	
	public List<FlightData> getFlights()
	{
		return datasource;
	}
	
	public FlightData findByNumber(Long flightNumber) {
		return getFlights().stream()
				.filter(flight -> flight.getFlightNumber() == flightNumber)
				.findFirst()
				.orElse(null);
	}
	
}

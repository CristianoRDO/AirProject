package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;
import br.edu.ifsp.dsw1.model.observer.FlightDataSubject;

public class FlightDataCollection implements FlightDataSubject{

	private List<FlightData> flights;
	private List<FlightDataObserver> observers;
	private FlightData lastUpdated;
	
	public FlightDataCollection() {
		this.flights = new LinkedList<FlightData>();
		this.observers = new LinkedList<FlightDataObserver>();
	}

	@Override
	public void register(FlightDataObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregister(FlightDataObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		observers.stream()
			.forEach(observer -> observer.update(lastUpdated));
	}
	
	public void insertFlight(FlightData flight) {
		if (flight != null) {
			lastUpdated = flight;
			flights.add(flight);
			notifyObservers();
		}
	}
	
	public void updateFlight(Long flightNumber) {
		var flight = findByNumber(flightNumber);
		if (flight != null) {
			flight.getState().change(flight);
			if (flight.getState() instanceof TookOff) {
				flights.remove(flight);
			}
			lastUpdated = flight;
			notifyObservers();
		}
	}
	
	public List<FlightData> getAllFligthts() {
		return new ArrayList<FlightData>(flights);
	}
	
	private FlightData findByNumber(Long flightNumber) {
		return flights.stream()
				.filter(flight -> flight.getFlightNumber().equals(flightNumber))
				.findFirst()
				.orElse(null);
	}
}


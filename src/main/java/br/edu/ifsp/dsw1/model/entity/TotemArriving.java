package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Arriving;

public class TotemArriving extends TotemModel{
	
	private static TotemArriving totemArrinving = null;
	
	public TotemArriving() {
		super();
	}
	
	@Override
	public void update(FlightData flight) {
		if(findByNumber(flight.getFlightNumber()) == null)
		{
			if(flight.getState() instanceof Arriving)
			{
				getFlights().add(new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime()));
			}
		}
		else
		{
            getFlights().removeIf(existingFlight -> existingFlight.getFlightNumber().equals(flight.getFlightNumber()));
		}	
	}
	
	public static TotemArriving getInstance() {
        if (totemArrinving == null) {
        	totemArrinving = new TotemArriving();
        }
        return totemArrinving;
    }
}

package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;

public class TotemTookOf extends TotemModel{

	public TotemTookOf() {
		super();
	}
	
	@Override
	public void update(FlightData flight) {
		if(findByNumber(flight.getFlightNumber()) == null)
		{
			if(flight.getState() instanceof TookOff)
			{
				getFlights().add(new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime()));
			}
		}
		else
		{
            getFlights().removeIf(existingFlight -> existingFlight.getFlightNumber().equals(flight.getFlightNumber()));
		}
		
	}

}

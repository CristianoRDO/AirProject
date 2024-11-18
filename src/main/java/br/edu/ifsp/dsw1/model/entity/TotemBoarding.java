package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Boarding;

public class TotemBoarding extends TotemModel {
	
	public TotemBoarding()
	{
		super();
	}

	@Override
	public void update(FlightData flight) {
		if(findByNumber(flight.getFlightNumber()) == null)
		{
			if(flight.getState() instanceof Boarding)
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

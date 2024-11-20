package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Boarding;

public class TotemBoarding extends TotemModel {
	
	private static TotemBoarding totemBoarding = null;
	
	private TotemBoarding()
	{
		super();
	}

	@Override
	public void update(FlightData flight) {
		if(findByNumber(flight.getFlightNumber()) == null)
		{
			if(flight.getState() instanceof Boarding)
			{
				FlightData flightTotem = new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime());
				flightTotem.setState(flight.getState());
				
				getFlights().add(flightTotem);
			}
		}
		else
		{
            getFlights().removeIf(existingFlight -> existingFlight.getFlightNumber().equals(flight.getFlightNumber()));
		}
	}
	
	public static TotemBoarding getInstance() {
        if (totemBoarding == null) {
        	totemBoarding = new TotemBoarding();
        }
        return totemBoarding;
    }

}

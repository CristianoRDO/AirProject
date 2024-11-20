package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.TakingOff;

public class TotemTakingOff extends TotemModel{
	
	private static TotemTakingOff totemTakingOff = null;

	public TotemTakingOff() {
		super();
	}
	
	@Override
	public void update(FlightData flight) {
		if(findByNumber(flight.getFlightNumber()) == null)
		{
			if(flight.getState() instanceof TakingOff)
			{
				getFlights().add(new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime()));
			}
		}
		else
		{
            getFlights().removeIf(existingFlight -> existingFlight.getFlightNumber().equals(flight.getFlightNumber()));
		}
	}
	
	public static TotemTakingOff getInstance() {
        if (totemTakingOff == null) {
        	totemTakingOff = new TotemTakingOff();
        }
        return totemTakingOff;
    }

}

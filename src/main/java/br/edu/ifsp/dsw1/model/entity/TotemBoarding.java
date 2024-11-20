package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import java.util.List;

public class TotemBoarding extends TotemModel {
	
	private static TotemBoarding totemBoarding = null;
	
	private TotemBoarding()
	{
		super();
	}

	@Override
	public void update(FlightData flight) 
	{
		if (flight != null) {
		    // Obter a lista de voos uma vez.
		    List<FlightData> flights = getFlights();
		    
		    // Verificar se o voo já existe.
		    FlightData existingFlight = findByNumber(flight.getFlightNumber());
		    
		    // Se o voo não existir, e o estado for Boarding, adicionamos no Totem.
		    if (existingFlight == null && flight.getState() instanceof Boarding) 
		    {
		        FlightData flightTotem = new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime());
		        flightTotem.setState(flight.getState());
		        flights.add(flightTotem);
		    } else if (existingFlight != null) {
		        // Se o voo já existe, removemos do Totem.
		        flights.remove(existingFlight);
		    }
		}
	}
	
	public static TotemBoarding getInstance() {
        if (totemBoarding == null) {
        	totemBoarding = new TotemBoarding();
        }
        return totemBoarding;
    }

}

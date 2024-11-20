package br.edu.ifsp.dsw1.model.entity;

import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;

public class TotemTookOff extends TotemModel{
	
	private static TotemTookOff totemTookOff = null;

	public TotemTookOff() {
		super();
	}
	
	@Override
	public void update(FlightData flight) {
		if (flight != null) {
		    // Obter a lista de voos uma vez.
		    List<FlightData> flights = getFlights();
		    
		    // Verificar se o voo já existe.
		    FlightData existingFlight = findByNumber(flight.getFlightNumber());
		    
		    // Se o voo não existir, e o estado for TookOff, adicionamos no Totem.
		    if (existingFlight == null && flight.getState() instanceof TookOff) 
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
	
	public static TotemTookOff getInstance() {
        if (totemTookOff == null) {
        	totemTookOff= new TotemTookOff();
        }
        return totemTookOff;
    }

}

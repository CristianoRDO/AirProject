package br.edu.ifsp.dsw1.model.entity;

import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.TakingOff;

public class TotemTakingOff extends TotemModel{
	
	private static TotemTakingOff totemTakingOff = null;

	public TotemTakingOff() {
		super();
	}
	
	@Override
	public void update(FlightData flight) {
		if (flight != null) {
		    // Obter a lista de voos uma vez.
		    List<FlightData> flights = getFlights();
		    
		    // Verificar se o voo já existe.
		    FlightData existingFlight = findByNumber(flight.getFlightNumber());
		    
		    // Se o voo não existir, e o estado for TakingOff, adicionamos no Totem.
		    if (existingFlight == null && flight.getState() instanceof TakingOff) 
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
	
	public static TotemTakingOff getInstance() {
        if (totemTakingOff == null) {
        	totemTakingOff = new TotemTakingOff();
        }
        return totemTakingOff;
    }

}

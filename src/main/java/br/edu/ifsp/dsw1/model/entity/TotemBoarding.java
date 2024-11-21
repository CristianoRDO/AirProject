package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import java.util.List;

/*
 * Classe concreta TotemBoarding que herda de TotemModel.
 * Representa um totem específico que gerencia voos no estado de embarque (Boarding).
 * Implementa o padrão Singleton para garantir que exista apenas uma instância do TotemBoarding em toda a aplicação.
 */
public class TotemBoarding extends TotemModel {
	
	private static TotemBoarding totemBoarding = null;
	
	/*
	 * Construtor privado para impedir a criação de instâncias fora da classe.
	 * Garante que o Singleton seja respeitado.
	 */
	private TotemBoarding()
	{
		super();
	}
	
	/*
	 * Método que implementa a lógica de atualização do totem com base nos dados do voo.
	 * Atualiza o totem adicionando ou removendo voos, dependendo de seu estado.
	 */

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
		        addList(flightTotem);
		    } else if (existingFlight != null) {
		        // Se o voo já existe, removemos do Totem.
		    	removeList(existingFlight);
		    }
		}
	}
	
	/*
	 * Método estático para obter a instância única da classe.
	 * Cria a instância se ela ainda não existir; caso contrário, retorna a instância existente. 
	 */
	
	public static TotemBoarding getInstance() {
        if (totemBoarding == null) {
        	totemBoarding = new TotemBoarding();
        }
        return totemBoarding;
    }

}

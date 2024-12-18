package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.Arriving;

/*
 * Classe concreta TotemArriving que herda de TotemModel.
 * Representa um totem específico que gerencia voos no estado de embarque (Arriving).
 * Implementa o padrão Singleton para garantir que exista apenas uma instância do TotemBoarding em toda a aplicação.
 */
public class TotemArriving extends TotemModel{
	
	private static TotemArriving totemArriving = null;
	
	/*
	 * Construtor privado para impedir a criação de instâncias fora da classe.
	 * Garante que o Singleton seja respeitado.
	 */
	private TotemArriving() {
		super();
	}
	
	/*
	 * Método que implementa a lógica de atualização do totem com base nos dados do voo.
	 * Atualiza o totem adicionando ou removendo voos, dependendo de seu estado.
	 */
	
	@Override
	public void update(FlightData flight) {
		if (flight != null) {
		   
		    // Se o estado do voo for Arriving, adicionamos no Totem.
		    if (flight.getState() instanceof Arriving) 
		    {
		        FlightData flightTotem = new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime());
		        flightTotem.setState(flight.getState());
		        addList(flightTotem);
		    } else{
		    	// Verificar se o voo existe na lista.
			    FlightData existingFlight = findByNumber(flight.getFlightNumber());
		    	if (existingFlight != null) {
		    		// Se o voo existe, removemos do Totem.
		    		removeList(existingFlight);
		    	} 
		    }
		}
	}
	
	/*
	 * Método estático para obter a instância única da classe.
	 * Cria a instância se ela ainda não existir; caso contrário, retorna a instância existente. 
	 */
	
	public static TotemArriving getInstance() {
        if (totemArriving == null) {
        	totemArriving = new TotemArriving();
        }
        return totemArriving;
    }
}

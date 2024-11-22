package br.edu.ifsp.dsw1.model.entity;

import br.edu.ifsp.dsw1.model.flightstates.TakingOff;

/*
 * Classe concreta TotemTakingOff que herda de TotemModel.
 * Representa um totem específico que gerencia voos no estado de embarque (TakingOff).
 * Implementa o padrão Singleton para garantir que exista apenas uma instância do TotemBoarding em toda a aplicação.
 */
public class TotemTakingOff extends TotemModel{
	
	private static TotemTakingOff totemTakingOff = null;

	/*
	 * Construtor privado para impedir a criação de instâncias fora da classe.
	 * Garante que o Singleton seja respeitado.
	 */
	private TotemTakingOff() {
		super();
	}
	
	/*
	 * Método que implementa a lógica de atualização do totem com base nos dados do voo.
	 * Atualiza o totem adicionando ou removendo voos, dependendo de seu estado.
	 */
	
	@Override
	public void update(FlightData flight) {
		if (flight != null) {
		    // Se o estado do voo for TakingOff, adicionamos no Totem.
		    if (flight.getState() instanceof TakingOff) 
		    {
		        FlightData flightTotem = new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime());
		        flightTotem.setState(flight.getState());
		        addList(flightTotem);
		    } else {
		    	// Verificar se o voo existe na lista.
		    	FlightData existingFlight = findByNumber(flight.getFlightNumber());
		    	// Se o voo existe, removemos do Totem.
		    	if (existingFlight != null){
		    		removeList(existingFlight);
		    	}
		    }
		}
	}
	
	/*
	 * Método estático para obter a instância única da classe.
	 * Cria a instância se ela ainda não existir; caso contrário, retorna a instância existente. 
	 */
	
	public static TotemTakingOff getInstance() {
        if (totemTakingOff == null) {
        	totemTakingOff = new TotemTakingOff();
        }
        return totemTakingOff;
    }

}

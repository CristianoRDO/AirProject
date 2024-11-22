package br.edu.ifsp.dsw1.model.entity;

import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;

/*
 * Classe concreta TotemTookOff que herda de TotemModel.
 * Representa um totem específico que gerencia voos no estado de embarque (TookOff).
 * Implementa o padrão Singleton para garantir que exista apenas uma instância do TotemBoarding em toda a aplicação.
 */

public class TotemTookOff extends TotemModel{
	
	private static TotemTookOff totemTookOff = null;
	
	/*
	 * Construtor privado para impedir a criação de instâncias fora da classe.
	 * Garante que o Singleton seja respeitado.
	 */
	private TotemTookOff() {
		super();
	}
	
	/*
	 * Método que implementa a lógica de atualização do totem com base nos dados do voo.
	 * Atualiza o totem adicionando ou removendo voos, dependendo de seu estado.
	 */
	
	@Override
	public void update(FlightData flight) {
		if (flight != null) {
		    // Se o voo não existir, e o estado for TookOff, adicionamos no Totem.
		    if (flight.getState() instanceof TookOff) 
		    {
		        FlightData flightTotem = new FlightData(flight.getFlightNumber(), flight.getCompany(), flight.getTime());
		        flightTotem.setState(flight.getState());
		        addList(flightTotem);
		    }
		}
	}
	
	/*
	 * Método estático para obter a instância única da classe.
	 * Cria a instância se ela ainda não existir; caso contrário, retorna a instância existente. 
	 */
	
	public static TotemTookOff getInstance() {
        if (totemTookOff == null) {
        	totemTookOff= new TotemTookOff();
        }
        return totemTookOff;
    }

}

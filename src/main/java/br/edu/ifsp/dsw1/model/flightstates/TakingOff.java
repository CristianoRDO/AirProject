package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class TakingOff implements State {
	// Instância única da classe, usada para garantir o padrão Singleton.
	private static TakingOff instance = null;
	
	// Construtor privado para garantir que apenas uma instância da classe seja criada (Singleton).
	private TakingOff() { }
	
	// Método getInstance: Retorna a instância única da classe TakingOff.
	public static TakingOff getIntance() {
		if (instance == null) {
			instance = new TakingOff();
		}
		return instance;
	}
	
	/*
     * Método change: Implementa a transição do estado do voo. Quando o voo está no estado de "TakingOff",
     * ele deve mudar para o estado de "TookOff". Este método altera o estado do voo para a próxima fase.
     */
	@Override
	public void change(FlightData flight) {
		flight.setState(TookOff.getIntance());
	}

}

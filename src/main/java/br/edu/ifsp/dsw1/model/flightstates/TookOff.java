package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class TookOff implements State {
	// Instância única da classe, usada para garantir o padrão Singleton.
	private static TookOff instance = null;
	
	// Construtor privado para garantir que apenas uma instância da classe seja criada (Singleton).
	private TookOff() { }
	
	// Método getInstance: Retorna a instância única da classe TookOff.
	public static TookOff getIntance() {
		if (instance == null) {
			instance = new TookOff();
		}
		return instance;
	}
	
	/*
     * Método change: Implementa a transição do estado do voo. Quando o voo está no estado de "TookOff",
     * nada acontece, pois, é o último estado de um voo.
     */
	@Override
	public void change(FlightData flight) {
		
	}

}

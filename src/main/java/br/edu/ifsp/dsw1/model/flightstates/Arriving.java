package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

/*
 * Classe Arriving: Implementa a interface State e representa o estado de "Embarque" de um voo.
 * Essa classe faz parte do padrão de design State, que permite que o comportamento de um voo
 * seja alterado dependendo de seu estado. O estado "Arriving" altera o estado do voo para "Boarding".
 */

public class Arriving implements State {
	// Instância única da classe, usada para garantir o padrão Singleton.
	private static Arriving instance = null;
	
	// Construtor privado para garantir que apenas uma instância da classe seja criada (Singleton).
	private Arriving() { }
	
	// Método getInstance: Retorna a instância única da classe Arriving.
	public static Arriving getIntance() {
		if (instance == null) {
			instance = new Arriving();
		}
		return instance;
	}
	
	/*
     * Método change: Implementa a transição do estado do voo. Quando o voo está no estado de "Arriving",
     * ele deve mudar para o estado de "Boarding". Este método altera o estado do voo para a próxima fase.
     */
	
	@Override
	public void change(FlightData flight) {
		flight.setState(Boarding.getIntance());
	}

}

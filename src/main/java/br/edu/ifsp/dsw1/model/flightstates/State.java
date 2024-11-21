package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

/* 
 * A interface State faz parte da implementação do padrão de projeto State, 
 * que permite que um objeto altere seu comportamento quando seu estado interno muda. 
 * Neste caso, ela representa os diferentes estados de um voo, permitindo que o 
 * comportamento de um voo (ou de qualquer objeto que o utilize) varie dependendo do seu estado atual.
 */

public interface State {
	
	/*
     * Método change: Este método é responsável por alterar o estado do voo
     * com base nas mudanças ocorridas. O estado de um voo pode ser modificado, e a implementação deste método
     * será diferente para cada estado específico.
     */
	
	void change(FlightData flight);
	
}

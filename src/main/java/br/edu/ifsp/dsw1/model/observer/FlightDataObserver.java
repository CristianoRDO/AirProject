package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.entity.FlightData;

/*
 * Interface FlightDataObserver: Parte do padrão Observer.
 * Define os métodos o observador (observer) deve implementar para ser notificado sobre mudanças em objetos;
 * Neste contexto, um observador é uma entidade que deseja ser notificada sobre a atualização de dados dos voos.
 */
public interface FlightDataObserver {

	 /*
	 * Método responsável por atualizar o estado dos totens.
     * Ele será chamado sempre que houver uma mudança no estado dos dados de um voo, passando o objeto `FlightData` atualizado.
     */
	void update(FlightData flight);
	
}

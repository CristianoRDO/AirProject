package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

/*
 * A classe TotemModel é uma classe abstrata que implementa o padrão Observer de FlightDataObserver.
 * Ela serve como base para a implementação futura dos diferentes tipos de Totens, ao qual,
 * cada Totem é responsável por armazenar voos de um determinado estado.
 */
public abstract class TotemModel implements FlightDataObserver{
	
	// Lista de dados de voo que o Totem irá monitorar e exibir. Essa lista é o "datasource" de voos do Totem.
	private List<FlightData> datasource;
	
	public TotemModel()
	{
		datasource = new ArrayList<FlightData>();
	}
	
	/*
	 *  Método que retorna uma cópia da lista de voos do Totem.
	 *  A lista é um dado central do Totem, usada para exibir os voos na interface.
	 */
	public List<FlightData> getFlights()
	{
		return new ArrayList<>(datasource);
	}
	
	/*
	 * Método que encontra um voo específico pelo número do voo (flightNumber).
	 * Utiliza o stream do Java para filtrar a lista de voos e encontra o voo que corresponde ao número fornecido.
	 * Se não encontrar, retorna null.
	 */
	public FlightData findByNumber(Long flightNumber) {
		return getFlights().stream()
				.filter(flight -> flight.getFlightNumber().equals(flightNumber))
				.findFirst()
				.orElse(null);
	}
	
}

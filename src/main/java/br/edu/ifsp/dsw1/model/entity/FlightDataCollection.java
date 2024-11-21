package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;
import br.edu.ifsp.dsw1.model.observer.FlightDataSubject;

/**
 * Classe responsável por atuar como repositório de dados do sistema.
 * Armazena e gerencia uma coleção de voos e notifica os observadores
 * sobre alterações nos dados, implementando o padrão Observer.
 */
public class FlightDataCollection implements FlightDataSubject{

	private List<FlightData> flights;
	private List<FlightDataObserver> observers;
	private FlightData lastUpdated;
	
	public FlightDataCollection() {
		this.flights = new LinkedList<FlightData>();
		this.observers = new LinkedList<FlightDataObserver>();
	}
	
    // Registra um novo observador para ser notificado sobre mudanças nos dados de voos.
	
	@Override
	public void register(FlightDataObserver observer) {
		observers.add(observer);
	}

	// Remove um observador da lista, deixando de notificá-lo.

	@Override
	public void unregister(FlightDataObserver observer) {
		observers.remove(observer);
	}
	

     //Notifica todos os observadores registrados sobre a última atualização realizada.
	
	@Override
	public void notifyObservers() {
		observers.stream()
			.forEach(observer -> observer.update(lastUpdated));
	}
	
	/**
     * Insere um novo voo na coleção de voos.
     * Também notifica os observadores sobre a adição.
     */
	public void insertFlight(FlightData flight) {
		if (flight != null) {
			lastUpdated = flight;
			flights.add(flight);
			notifyObservers();
		}
	}
	
	/**
     * Atualiza o estado de um voo existente na coleção com base no número do voo.
     * Remove o voo se ele atingir o estado "TookOff".
     */
	public void updateFlight(Long flightNumber) {
		var flight = findByNumber(flightNumber);
		if (flight != null) {
			flight.getState().change(flight);
			if (flight.getState() instanceof TookOff) {
				flights.remove(flight);
			}
			lastUpdated = flight;
			notifyObservers();
		}
	}
	
	/**
     * Retorna uma cópia da lista de todos os voos registrados.
     * Garante que a lista original não seja modificada por referências externas.
     * */
	public List<FlightData> getAllFligthts() {
		return new ArrayList<FlightData>(flights);
	}
	
	//Busca um voo específico na coleção com base no número do voo.
	private FlightData findByNumber(Long flightNumber) {
		return flights.stream()
				.filter(flight -> flight.getFlightNumber().equals(flightNumber))
				.findFirst()
				.orElse(null);
	}
}


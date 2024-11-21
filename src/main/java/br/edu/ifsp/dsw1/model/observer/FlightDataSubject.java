package br.edu.ifsp.dsw1.model.observer;

/*
 * O objetivo geral da classe FlightDataSubject é fornecer uma interface para objetos que desejam seguir o padrão Observer, 
 * permitindo que eles notifiquem de forma eficiente e desacoplada os observadores sobre mudanças no estado dos dados de voo.
 * */

public interface FlightDataSubject {
	
    //Registra um observer para receber notificações de mudanças no estado do Subject.
	void register(FlightDataObserver observer);
	
	//Remove um observer previamente registrado.
	void unregister(FlightDataObserver observer);
	
	/*
     * Notifica todos os observadores registrados sobre uma mudança no estado do Voo.
     * Este método chama o método de atualização de cada observer para que eles possam
     * reagir à mudança de estado.
     */
	void notifyObservers();
}

package observer;

public interface Observer {

    public void update(Object event);

    // Se necesita para identificar el nombre de cada observer.
    // public String getName();

}

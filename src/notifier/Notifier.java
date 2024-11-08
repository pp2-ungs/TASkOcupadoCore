package notifier;

import observer.Observer;

public interface Notifier extends Observer {
    
    public void update(Object event);

}

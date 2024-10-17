package remote;
 
import java.rmi.Remote; 
import java.rmi.RemoteException;
import observer.Observer;

// permitime dudar
public abstract class RemoteObserver implements Remote, Observer {
    
    @Override
    public void update(Object event) { // throws RemoteException
         
    }
    
}

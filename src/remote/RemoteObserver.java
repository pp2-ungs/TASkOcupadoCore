package remote;
 
import observer.Observer;
import java.rmi.Remote;
import java.rmi.RemoteException;

// TODO: está un poco de más?
public interface RemoteObserver extends Remote, Observer {
    
    @Override
    public void update(Object event) throws RemoteException;  // Mantener RemoteException

}

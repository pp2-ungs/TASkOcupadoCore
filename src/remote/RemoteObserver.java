package remote;
 
import observer.Observer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// permitime dudar
public interface RemoteObserver extends Remote, Observer {

    public void update(Object event) throws RemoteException;  // Mantener RemoteException

}

package remote;
 
import observer.Observer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// permitime dudar
public class RemoteObserver extends UnicastRemoteObject implements Remote {
    private Observer observer;

    public RemoteObserver(Observer obs) throws RemoteException {
        this.observer = obs;
    }
    
    protected Observer getObserver() throws RemoteException {
        return observer;
    }

}

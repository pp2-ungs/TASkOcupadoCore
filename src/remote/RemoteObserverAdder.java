package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserverAdder extends Remote {

    public void addObserver(RemoteObserver observer) throws RemoteException;

}

package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import observer.Observer;

public interface RemoteTaskController extends Remote {
    void addObserver(RemoteObserver observer) throws RemoteException;
}

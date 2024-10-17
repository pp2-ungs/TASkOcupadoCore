package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

// TODO: rename
public interface RemoteTaskController extends Remote {
    void addObserver(RemoteObserver observer) throws RemoteException;
}

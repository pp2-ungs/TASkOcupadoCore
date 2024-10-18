package observer;

import java.rmi.RemoteException;

public interface Observer {

    public void update(Object event) throws RemoteException;

}

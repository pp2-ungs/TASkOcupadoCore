package observer;

import java.rmi.RemoteException;

public interface Observer {

    // TODO: no queda otra que dejarlo así
    public void update(Object event) throws RemoteException;
    
}

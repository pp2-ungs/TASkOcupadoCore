package remote;

import core.TaskAssigner;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class RemoteServer {

    public void startServer(TaskAssigner taskAssigner) {
        try {
            RemoteAssigner remoteController = new RemoteAssigner(taskAssigner);
            Registry createRegistry = LocateRegistry.createRegistry(5050);
            createRegistry.rebind("TASkOcupado", remoteController);

            System.out.println(createRegistry + "Server started.... ");
        } catch (RemoteException ex) {
            System.err.println("?remote server failed " + ex.getMessage());
        }
    }

}

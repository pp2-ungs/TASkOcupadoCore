
package remote;

import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class RemoteServer {
    public static void startServer(){ 
        try { 
            RemoteController c = new RemoteController(); 
            Registry createRegistry = LocateRegistry.createRegistry(5050); 
            createRegistry.rebind("server", c); 
            System.out.println("Server started.... "); 
        } catch (RemoteException ex)  {
            System.err.println("?Remote server failed " + ex.getMessage());
        } 
    } 
    public static void main(String[] args)  { 
        startServer(); 
    }
}

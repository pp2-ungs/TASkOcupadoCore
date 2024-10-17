package remote;

import core.TaskAssigner;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteController extends UnicastRemoteObject implements RemoteTaskController {
    TaskAssigner taskAssigner;
    
    // TODO: taskAssigner, no? o TASkOcupado? depende de la us4
    public RemoteController(TaskAssigner taskAssigner) throws RemoteException {
        this.taskAssigner = taskAssigner;
    }
    
    public synchronized void addObserver(RemoteObserver observer) throws RemoteException {
        taskAssigner.addObserver(observer);
    }
}

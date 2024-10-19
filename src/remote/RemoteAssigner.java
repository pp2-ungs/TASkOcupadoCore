package remote;

import core.TaskAssigner;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteAssigner extends UnicastRemoteObject implements RemoteObserverAdder {

    private TaskAssigner taskAssigner;

    public RemoteAssigner(TaskAssigner taskAssigner) throws RemoteException {
        this.taskAssigner = taskAssigner;
    }

    public synchronized void addObserver(RemoteObserver observer) throws RemoteException {
        taskAssigner.addObserver(observer);
    }
}

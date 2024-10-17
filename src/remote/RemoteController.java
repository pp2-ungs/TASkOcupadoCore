/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package remote;

import core.TaskAssigner;
import java.rmi.Remote;
import java.rmi.RemoteException;
import observer.Observer;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;

    // hace falta RemoteTaskController?
public class RemoteController extends UnicastRemoteObject implements RemoteTaskController {
    TaskAssigner taskAssigner;
    
    // se tiene que construir de alguna manera... necesita saber a TaskAssigner (o TASkOcupado, para más logeos(?)
    public RemoteController(TaskAssigner taskAssigner) throws RemoteException {
        this.taskAssigner = taskAssigner;
    }
    
    // deberíamos exponer dos cosas: Observer (o RemoteObserver, pero trae problemas) y RemoteController
    public synchronized void addObserver(RemoteObserver observer) throws RemoteException {
        taskAssigner.addObserver(observer);
    }
    
}

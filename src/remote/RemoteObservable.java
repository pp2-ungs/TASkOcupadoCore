/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package remote;

import java.rmi.Remote;
import observer.Observable;

public interface RemoteObservable extends Remote, Observable {
    // ???? necesaria?? o va en TaskAssigner?
    
    public void addObserver(RemoteObserver observer);
}

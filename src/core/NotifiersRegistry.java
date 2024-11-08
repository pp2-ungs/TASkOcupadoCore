/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author xime
 */
public class NotifiersRegistry {
    Set<Notifier> all; //deactivated
    NotifiersActivator activator;
    
    void activate(String obs) {
        activator.activeNotifiers(all.get());
    }
    
    void deactivate(String obs) {
        activator.deactivateNotifier(all.get());
    }
    
    Set<Notifier> getActiveNotifiers() {
        return activator.getActiveNotifiers();
    }
    
    Set<String> getAll() {
        return all.toString();
    }
    
}

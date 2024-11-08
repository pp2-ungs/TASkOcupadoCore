/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author xime
 */
public class NotifiersActivator {
    
    Set<Notifier> activeNotifiers;
    
    
    //NotifiersRegistry registry;
    
    void activate(Notifier obs) {
        activeNotifiers.add(registry.getNotifier(obs));
    }
    
    void deactivate(Notifier obs) {
        activeNotifiers.remove(registry.getNotifier(obs));
    }
    
    Set<Notifier> getActiveNotifiers() {
        return activeNotifiers;
    }
    
}

package notifier;

import java.util.HashSet;
import java.util.Set;

public class NotifiersActivator {

    private Set<ActivatableNotifier> activatableNotifiers;

    public void addNotifier(Notifier notifier) {
        activatableNotifiers.add(new ActivatableNotifier(notifier));
    }

    public void activateNotifier(String notifier) {
        var activatableNotifier = getActivatableNotifier(notifier);
        if (activatableNotifier != null) activatableNotifier.active();
    }

    public void deactivateNotifier(String notifier) {
        var activatableNotifier = getActivatableNotifier(notifier);
        if (activatableNotifier != null) activatableNotifier.deactive();
    }

    private ActivatableNotifier getActivatableNotifier(String notifier) {
        for (ActivatableNotifier activatableNotifier : activatableNotifiers) {
            if (activatableNotifier.getName().equals(notifier)) {
                return activatableNotifier;
            }
        }
        return null;
    }

    public Set<Notifier> getActiveNotifiers() {
        Set<Notifier> activeNotifiers = new HashSet<>();
        for (ActivatableNotifier activatableNotifier : activatableNotifiers) {
            if (activatableNotifier.isActive()) {
                activeNotifiers.add(activatableNotifier.getNotifier());
            }
        }
        return activeNotifiers;
    }

    public Set<Notifier> getNotifiers() {
        Set<Notifier> notifiers = new HashSet<>();
        activatableNotifiers.forEach(activatableNotifier -> notifiers.add(activatableNotifier.getNotifier()));
        return notifiers;
    }
}

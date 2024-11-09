package notifier;

public class ActivatableNotifier {
    
    private Notifier notifier;
    private boolean isActive;
    
    public ActivatableNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
    
    public void activateNotifier() {
        isActive = true;
    }
    
    public void deactivateNotifier() {
        isActive = false;
    }
    
    public Notifier getNotifier() {
        return notifier;
    }
    
    public String getName() {
        return notifier.getClass().getSimpleName();
    }
    
    public boolean isActive() {
        return isActive;
    }
    
}

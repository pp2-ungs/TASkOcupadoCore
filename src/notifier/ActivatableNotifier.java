package notifier;

public class ActivatableNotifier {
    
    private Notifier notifier;
    private boolean isActive;
    
    public ActivatableNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
    
    public void activate() {
        isActive = true;
    }
    
    public void deactivate() {
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

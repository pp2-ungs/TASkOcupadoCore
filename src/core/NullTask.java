package core;

public class NullTask extends Task {
    
    public NullTask() {
        super("Null Task");
    }
    
    public boolean isNull() {
        return true;
    }
}

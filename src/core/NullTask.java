package core;

// ??? Esta clase queda, ó dijo Javier que hay que sacarla?
public class NullTask extends Task {
    
    public NullTask() {
        super("Null Task");
    }
    
    public boolean isNull() {
        return true;
    }
}

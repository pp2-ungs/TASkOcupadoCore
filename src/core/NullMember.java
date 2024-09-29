package core;

public class NullMember extends Member {
    
    public NullMember() {
        super("Null Member", "Null Email");
    }
    
    public boolean isNull() {
        return true;
    }
}

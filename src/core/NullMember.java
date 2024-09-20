package core;

public class NullMember extends Member {
    
    public NullMember() {
        super("Null Member");
    }
    
    public boolean isNull() {
        return true;
    }
}

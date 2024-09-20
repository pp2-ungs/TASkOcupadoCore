package core;

public class NullMember extends Member {
    
    public NullMember() {
        super("Null Member", "null email");
    }
    
    public boolean isNull() {
        return true;
    }
}

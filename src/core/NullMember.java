package core;

// ??? Esta clase queda, ó dijo Javier que hay que sacarla?
public class NullMember extends Member {
    
    public NullMember() {
        super("Null Member", "Null Email");
    }
    
    public boolean isNull() {
        return true;
    }
}

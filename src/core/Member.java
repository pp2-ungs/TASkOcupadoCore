package core;

import java.util.Objects;

// Clase vacía: los miembros se traen un calendario externo.
public class Member {

    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            throw new IllegalArgumentException("?wrong argument " + obj.getClass() + ": " + obj);
        }
        return this == obj || this.name.equals(((Member) obj).name);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return name;
    }

}

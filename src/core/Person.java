package core;

import java.util.Objects;

public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            throw new IllegalArgumentException("?wrong argument type " + obj.getClass() + ": " + obj);
        }
        return this == obj || this.name.equals(((Person) obj).name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}

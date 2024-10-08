package core;

import java.util.Objects;

public class Task {

    private String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            throw new IllegalArgumentException("?wrong argument type " + obj.getClass() + ": " + obj);
        }
        return this == obj || this.description.equals(((Task) obj).description);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

}

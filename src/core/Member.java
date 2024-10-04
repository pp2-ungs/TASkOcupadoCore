package core;

import java.util.Objects;

// DONE
public class Member {

    private String name;
    private String email;
    private int telegramId;

    public Member(String name, String email, int telegramId) {
        this.name = name;
        this.email = email;
        this.telegramId = telegramId;
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
        return this == obj || this.name.equals(((Member) obj).name);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTelegramId() {
        return telegramId;
    }

    @Override
    public String toString() {
        return name;
    }

}

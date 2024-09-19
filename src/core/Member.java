package core;

import java.util.Objects;

// Clase vacía: los miembros se traen un calendario externo.
public class Member {
	
	private String name;
	
	public Member() { }

	public Member(String name) {
		this.name = name;
	}
	
	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
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

	@Override
	public String toString() {
		return name;
	}

}
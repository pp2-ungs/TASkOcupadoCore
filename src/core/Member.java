package core;

import java.util.Objects;

// Clase vacía: los miembros se traen un calendario externo.
public class Member {
	
	private String name;

	public Member(String name) {
		this.name = name;
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
		if (this == obj)
			return true;
		if (!(obj instanceof Member))
			return false;
		Member other = (Member) obj;
		return name.equals(other.name);
	}
	
	

}
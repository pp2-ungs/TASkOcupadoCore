package core;

import java.util.Objects;

// Clase vacía: las tareas se traen un calendario externo.
public class Task {
	
	private String description;
	
	public Task(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		return description.equals(other.description);
	}
	
	

}

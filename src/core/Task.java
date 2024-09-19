package core;

import java.util.Objects;

// Clase vacía: las tareas se traen un calendario externo.
public class Task {
	
	private String description;
	
	public Task() { }
	
	public Task(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String d) {
		description = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {
			throw new IllegalArgumentException("?wrong argument " + obj.getClass() + ": " + obj);
		}
		return this == obj || this.description.equals(((Task) obj).description);
	}

	@Override
	public String toString() {
		return description;
	}
}

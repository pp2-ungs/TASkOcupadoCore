package service;

import core.TASkOcupado;

public class TASkOcupadoFactory {

	public static TASkOcupado create(String path) {
		return new TASkOcupado(TaskAssignerFactory.create(path));
	}

}

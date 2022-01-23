package controller;

import java.util.ArrayList;

import model.Paciente;

public class PacienteController {
	
	public static boolean incluir(String[] paciente) {
		Paciente pac = new Paciente(paciente);
		
		if (pac.incluir()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<String[]> consultar(String busca) {
		ArrayList<String[]> pacientes = new ArrayList<String[]>();
		pacientes = Paciente.consultar(busca);
		return pacientes;
	}
	
	public static boolean editar(String[] paciente) {
		if (Paciente.editar(paciente)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean excluir(String cpf) {
		if (Paciente.excluir(cpf)) {
			return true;
		} else {
			return false;
		}
	}
}

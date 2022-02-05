package controller;

import java.util.ArrayList;

import model.Consulta;

public class ConsultaController {
	
	public static boolean incluir(String[] paciente) {
		Paciente pac = new Paciente(paciente);
		
		if (pac.incluir()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<String[]> consultar(String[] busca) {
		ArrayList<String[]> consultas = new ArrayList<String[]>();
		consultas = Consulta.consultar(busca);
		return consultas;
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

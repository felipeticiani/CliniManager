package controller;

import java.util.ArrayList;

import model.Medico;

public class MedicoController {
	
	public static boolean incluir(String[] medico) {
		Medico med = new Medico(medico);
		
		if (med.incluir()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<String[]> consultar(String busca) {
		ArrayList<String[]> medicos = new ArrayList<String[]>();
		medicos = Medico.consultar(busca);
		return medicos;
	}
	
	public static ArrayList<String> consultarEspecialidades() {
		ArrayList<String> especialidades = new ArrayList<String>();
		especialidades = Medico.consultarEspecialidades();
		return especialidades;
	}
	
	public static boolean editar(String[] medico) {
		if (Medico.editar(medico)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean excluir(String crm) {
		if (Medico.excluir(crm)) {
			return true;
		} else {
			return false;
		}
	}
}

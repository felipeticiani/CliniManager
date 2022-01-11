package controller;

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
	
	public static String[] consultar(String busca) {
		String medico[] = new String[6]; 
		medico = Medico.consultar(busca);
		return medico;
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

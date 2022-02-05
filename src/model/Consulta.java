package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Consulta {
	// Variáveis
	private String data, horario, fk_medico, fk_paciente;
	
	// Construtores
	public Consulta(String[] consulta) {
		this.data = consulta[0];
		this.horario = consulta[1];
		this.fk_medico = consulta[2];
		this.fk_paciente = consulta[3];
	}
	
	// Métodos
	public boolean incluir() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		
		boolean conexao = false;
		
		try {
			sql = "INSERT INTO paciente (nome, cpf, data_nascimento, email, endereco, telefone) VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?)";
			ps = banco.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, cpf);
			ps.setString(3, dataNascimento);
			ps.setString(4, email);
			ps.setString(5, endereco);
			ps.setString(6, telefone);
			ps.executeUpdate();
			ps.close();
			banco.close();
			conexao = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static ArrayList<String[]> consultar(String[] busca) {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		
		ArrayList<String[]> consultas = new ArrayList<String[]>();
		
		try {
			if (busca[0].isBlank()) {
				sql = "SELECT DATE_FORMAT(consulta.data, '%d/%m/%Y') as data, "
						+ "TIME_FORMAT(consulta.horario, '%H:%i') as horario, "
						+ "medico.nome as medico, paciente.nome as paciente "
						+ "FROM consulta INNER JOIN medico ON consulta.fk_medico = medico.crm "
						+ "INNER JOIN paciente ON consulta.fk_paciente = paciente.cpf "
						+ "WHERE consulta.data = CURRENT_DATE()";
			} else {
				sql = "SELECT DATE_FORMAT(consulta.data, '%d/%m/%Y') as data, "
						+ "TIME_FORMAT(consulta.horario, '%H:%i') as horario, "
						+ "medico.nome as medico, paciente.nome as paciente "
						+ "FROM consulta INNER JOIN medico ON consulta.fk_medico = medico.crm "
						+ "INNER JOIN paciente ON consulta.fk_paciente = paciente.cpf "
						+ "WHERE data BETWEEN STR_TO_DATE('" + busca[0] + "', '%d/%m/%Y')"
								+ "AND STR_TO_DATE('" + busca[1] + "', '%d/%m/%Y')";
			}
			ps = banco.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String consulta[] = new String[4];
				consulta[0] = rs.getString("data");
				consulta[1] = rs.getString("horario");
				consulta[2] = rs.getString("medico");
				consulta[3] = rs.getString("paciente");
				consultas.add(consulta);
			}
			ps.close();
			banco.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return consultas;
	}
	
	public static boolean editar(String[] paciente) {
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		boolean conexao = false;
		
		try {
			sql = "UPDATE paciente SET nome = ?, cpf = ?, data_nascimento = STR_TO_DATE(?, '%d/%m/%Y'), email = ?, endereco = ?, telefone = ? WHERE cpf = " + paciente[6];
			ps = banco.prepareStatement(sql);
			ps.setString(1, paciente[0]);
			ps.setString(2, paciente[1]);
			ps.setString(3, paciente[2]);
			ps.setString(4, paciente[3]);
			ps.setString(5, paciente[4]);
			ps.setString(6, paciente[5]);
			ps.executeUpdate();
			ps.close();
			banco.close();
			conexao = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static boolean excluir(String cpf) {
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		boolean conexao = false;
		
		try {
			sql = "UPDATE paciente SET status = 0 WHERE cpf = " + cpf;
			ps = banco.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			banco.close();
			conexao = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static Connection abreBanco() {
		final String BANCO = "jdbc:mysql://localhost:3306/bd_clinimanager";
		try {
			return DriverManager.getConnection(BANCO, "root", "");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

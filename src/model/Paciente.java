package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Paciente {
	// Variáveis
	private String nome, cpf, dataNascimento, email, endereco, telefone;
	
	// Construtores
	public Paciente(String[] paciente) {
		this(paciente[1]);
		this.nome = paciente[0];
		this.dataNascimento = paciente[2];
		this.email = paciente[3];
		this.endereco = paciente[4];
		this.telefone = paciente[5];
	}
	public Paciente (String cpf) {
		this.cpf = cpf;
	}
	
	// Métodos
	public boolean incluir() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		
		boolean conexao = false;
		
		try {
			sql = "INSERT INTO paciente (nome, cpf, data_nascimento, email, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?)";
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
	
	public static ArrayList<String[]> consultar(String busca) {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		
		ArrayList<String[]> pacientes = new ArrayList<String[]>();
		
		try {
			sql = "SELECT * FROM paciente WHERE cpf LIKE '%" + busca + "%' OR nome LIKE '%" + busca + "%' LIMIT 20";
			ps = banco.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String paciente[] = new String[6];
				paciente[0] = rs.getString("cpf");
				paciente[1] = rs.getString("nome");
				paciente[2] = rs.getString("data_nascimento");
				paciente[3] = rs.getString("email");
				paciente[4] = rs.getString("endereco");
				paciente[5] = rs.getString("telefone");
				pacientes.add(paciente);
			}
			ps.close();
			banco.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return pacientes;
	}
	
	public static boolean editar(String[] paciente) {
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		boolean conexao = false;
		
		try {
			sql = "UPDATE paciente SET nome = ?, cpf = ?, data_nascimento = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = " + paciente[1];
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
			sql = "DELETE FROM paciente WHERE cpf = " + cpf;
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

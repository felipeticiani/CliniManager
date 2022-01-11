package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medico {
	// Variáveis
	private String nome, telefone, especialidade, email, senha, crm;
	
	// Construtores
	public Medico(String[] medico) {
		this(medico[1]);
		this.nome = medico[0];
		this.especialidade = medico[2];
		this.email = medico[3];
		this.telefone = medico[4];
		this.senha = medico[5];
	}
	public Medico (String crm) {
		this.crm = crm;
	}
	
	// Métodos
	public boolean incluir() {
		System.out.println("Entrou no método Incluir");
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		
		boolean conexao = false;
		
		try {
			sql = "INSERT INTO medico (nome, crm, especialidade, email, telefone, senha) VALUES (?, ?, ?, ?, ?, ?)";
			ps = banco.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, crm);
			ps.setString(3, especialidade);
			ps.setString(4, email);
			ps.setString(5, telefone);
			ps.setString(6, senha);
			ps.executeUpdate();
			ps.close();
			banco.close();
			conexao = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static String[] consultar(String busca) {
		System.out.println("Entrou no método Consultar");
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		
		String medico[] = new String[6];
		
		try {
			sql = "SELECT * FROM medico WHERE crm = '" + busca + "' OR nome LIKE '%" + busca + "%'";
			ps = banco.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				medico[0] = rs.getString("crm");
				medico[1] = rs.getString("nome");
				medico[2] = rs.getString("telefone");
				medico[3] = rs.getString("email");
				medico[4] = rs.getString("senha");
				medico[5] = rs.getString("especialidade");
			}
			ps.close();
			banco.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return medico;
	}
	
	public static boolean editar(String[] medico) {
		System.out.println("Entrou no método Editar");
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		boolean conexao = false;
		
		try {
			sql = "UPDATE medico SET nome = ?, crm = ?, especialidade = ?, email = ?, telefone = ?, senha = ? WHERE crm = " + medico[1];
			ps = banco.prepareStatement(sql);
			ps.setString(1, medico[0]);
			ps.setString(2, medico[1]);
			ps.setString(3, medico[2]);
			ps.setString(4, medico[3]);
			ps.setString(5, medico[4]);
			ps.setString(6, medico[5]);
			ps.executeUpdate();
			ps.close();
			banco.close();
			conexao = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static boolean excluir(String crm) {
		System.out.println("Entrou no método Excluir");
		Connection banco = abreBanco();
		PreparedStatement ps;
		String sql;
		boolean conexao = false;
		
		try {
			sql = "DELETE FROM medico WHERE crm = " + crm;
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

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.PacienteController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PacCadastrar {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textNascimento;
	private JTextField textEmail;
	private JTextField textEndereco;
	private JTextField textTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacCadastrar window = new PacCadastrar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PacCadastrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("CliniManager");
		frame.setBounds(100, 100, 619, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNovo = new JLabel("Novo paciente");
		lblNovo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNovo.setBounds(25, 24, 125, 20);
		frame.getContentPane().add(lblNovo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNome.setBounds(25, 60, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCpf.setBounds(25, 138, 46, 14);
		frame.getContentPane().add(lblCpf);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEndereco.setBounds(25, 213, 72, 14);
		frame.getContentPane().add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefone.setBounds(378, 60, 60, 14);
		frame.getContentPane().add(lblTelefone);
		
		JLabel lblNascimento = new JLabel("Data de Nascimento");
		lblNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNascimento.setBounds(188, 138, 107, 14);
		frame.getContentPane().add(lblNascimento);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmail.setBounds(378, 138, 85, 14);
		frame.getContentPane().add(lblEmail);
		
		textNome = new JTextField();
		textNome.setBounds(25, 84, 315, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setColumns(10);
		textTelefone.setBounds(378, 84, 199, 20);
		frame.getContentPane().add(textTelefone);
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		textCpf.setBounds(25, 163, 125, 20);
		frame.getContentPane().add(textCpf);
		
		textNascimento = new JTextField();
		textNascimento.setColumns(10);
		textNascimento.setBounds(188, 163, 152, 20);
		frame.getContentPane().add(textNascimento);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(378, 163, 199, 20);
		frame.getContentPane().add(textEmail);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(25, 238, 315, 20);
		frame.getContentPane().add(textEndereco);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paciente[] = new String[6];
				paciente[0] = textNome.getText();
				paciente[1] = textCpf.getText();
				String[] dt = textNascimento.getText().split("/");
				StringBuffer data = new StringBuffer(dt[2]);
				data.append("-" + dt[1] + "-" + dt[0]);
				paciente[2] = data.toString();
				if (!textEmail.getText().isBlank())
					paciente[3] = textEmail.getText();
				if (!textEndereco.getText().isBlank()) {
					paciente[4] = textEndereco.getText();
				}
				if (!textTelefone.getText().isBlank())
					paciente[5] = textTelefone.getText();
				
				if (PacienteController.incluir(paciente)) {
					textNome.setText("");
					textTelefone.setText("");
					textCpf.setText("");
					textNascimento.setText("");
					textEmail.setText("");
					textEndereco.setText("");
					JOptionPane.showMessageDialog(frame, "Paciente incluído com sucesso!");
					
				} else {
					System.out.println("Erro na inclusão!");
					JOptionPane.showMessageDialog(frame, "Erro na inclusão!");
				}
			}
		});
		btnSalvar.setBounds(366, 335, 89, 23);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PacInicio.main(null);
			}
		});
		btnCancelar.setBounds(488, 335, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
	
}

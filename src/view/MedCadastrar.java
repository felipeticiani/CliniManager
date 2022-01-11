package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.MedicoController;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedCadastrar {

	private JFrame frame;
	private JTextField textMedNome;
	private JTextField textMedTelefone;
	private JTextField textMedCrm;
	private JTextField textMedEspecialidade;
	private JTextField textMedEmail;
	private JPasswordField textMedSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedCadastrar window = new MedCadastrar();
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
	public MedCadastrar() {
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
		
		JLabel lblNovoMedico = new JLabel("Novo m\u00E9dico");
		lblNovoMedico.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNovoMedico.setBounds(25, 24, 105, 14);
		frame.getContentPane().add(lblNovoMedico);
		
		JLabel lblMedNome = new JLabel("Nome");
		lblMedNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMedNome.setBounds(25, 60, 46, 14);
		frame.getContentPane().add(lblMedNome);
		
		JLabel lblMedCrm = new JLabel("CRM");
		lblMedCrm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMedCrm.setBounds(25, 138, 46, 14);
		frame.getContentPane().add(lblMedCrm);
		
		JLabel lblMedSenha = new JLabel("Senha");
		lblMedSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMedSenha.setBounds(25, 213, 46, 14);
		frame.getContentPane().add(lblMedSenha);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefone.setBounds(378, 60, 60, 14);
		frame.getContentPane().add(lblTelefone);
		
		JLabel lblEspecialidade = new JLabel("Especialidade");
		lblEspecialidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEspecialidade.setBounds(188, 138, 85, 14);
		frame.getContentPane().add(lblEspecialidade);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmail.setBounds(378, 138, 85, 14);
		frame.getContentPane().add(lblEmail);
		
		textMedNome = new JTextField();
		textMedNome.setBounds(25, 84, 315, 20);
		frame.getContentPane().add(textMedNome);
		textMedNome.setColumns(10);
		
		textMedTelefone = new JTextField();
		textMedTelefone.setColumns(10);
		textMedTelefone.setBounds(378, 84, 199, 20);
		frame.getContentPane().add(textMedTelefone);
		
		textMedCrm = new JTextField();
		textMedCrm.setColumns(10);
		textMedCrm.setBounds(25, 163, 125, 20);
		frame.getContentPane().add(textMedCrm);
		
		textMedEspecialidade = new JTextField();
		textMedEspecialidade.setColumns(10);
		textMedEspecialidade.setBounds(188, 163, 152, 20);
		frame.getContentPane().add(textMedEspecialidade);
		
		textMedEmail = new JTextField();
		textMedEmail.setColumns(10);
		textMedEmail.setBounds(378, 163, 199, 20);
		frame.getContentPane().add(textMedEmail);
		
		textMedSenha = new JPasswordField();
		textMedSenha.setBounds(25, 238, 125, 20);
		frame.getContentPane().add(textMedSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medico[] = new String[6];
				medico[0] = textMedNome.getText();
				medico[1] = textMedCrm.getText();
				medico[2] = textMedEspecialidade.getText();
				if (!textMedEmail.getText().isBlank())
					medico[3] = textMedEmail.getText();
				if (!textMedTelefone.getText().isBlank())
					medico[4] = textMedTelefone.getText();
				medico[5] = textMedSenha.getText();
				
				if (MedicoController.incluir(medico)) {
					textMedNome.setText("");
					textMedTelefone.setText("");
					textMedCrm.setText("");
					textMedEspecialidade.setText("");
					textMedEmail.setText("");
					textMedSenha.setText("");
					JOptionPane.showMessageDialog(frame, "Médico incluído com sucesso!");
					
				} else {
					System.out.println("Erro na inclus�o!");
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
				MedInicio.main(null);
			}
		});
		btnCancelar.setBounds(488, 335, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
	
}

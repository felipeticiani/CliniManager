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

public class MedVisualizar {

	private JFrame frame;
	private JTextField textMedNome;
	private JTextField textMedTelefone;
	private JTextField textMedCrm;
	private JTextField textMedEspecialidade;
	private JTextField textMedEmail;
	private JPasswordField textMedSenha;
	private boolean editando;
	
	public void preencheDados(String[] medico) {
		textMedCrm.setText(medico[0]);
		textMedNome.setText(medico[1]);
		textMedTelefone.setText(medico[2]);
		textMedEmail.setText(medico[3]);
		textMedSenha.setText(medico[4]);
		textMedEspecialidade.setText(medico[5]);
	}
	
	public void alteraEdicao(boolean edicao) {
		textMedCrm.setEditable(edicao);
		textMedNome.setEditable(edicao);
		textMedTelefone.setEditable(edicao);
		textMedEmail.setEditable(edicao);
		textMedSenha.setEditable(edicao);
		textMedEspecialidade.setEditable(edicao);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String med) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedVisualizar window = new MedVisualizar();
					try {
						window.frame.setVisible(true);
						String[] medico = MedicoController.consultar(med);
						if (medico[1] == null) {
							JOptionPane.showMessageDialog(window.frame, "Nenhum m�dico encontrado!");
							window.frame.dispose();
							MedInicio.main(null);
						} else {
							window.preencheDados(medico);
							window.alteraEdicao(false);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						JOptionPane.showMessageDialog(window.frame, "Dados inv�lidos!");
						window.frame.dispose();
						MedInicio.main(null);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MedVisualizar() {
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
		
		JLabel lblMedico = new JLabel("Médico");
		lblMedico.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblMedico.setBounds(25, 24, 105, 14);
		frame.getContentPane().add(lblMedico);
		
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
		
		// Vari�veis para os bot�es
		JButton btnExcluir = new JButton("Excluir");
		JButton btnSalvar = new JButton("Salvar");
		JButton btnEditar = new JButton("Editar");
		JButton btnVoltar = new JButton("Voltar");
		
		// Bot�o Excluir
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opcoes = {"Cancelar", "Excluir"};
				int opcao = JOptionPane.showOptionDialog(btnExcluir, "Tem certeza que deseja excluir?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
				if (opcao == 1) {
					if (MedicoController.excluir(textMedCrm.getText())) {
						JOptionPane.showMessageDialog(frame, "Médico excluído com sucesso!");
						frame.dispose();
						MedInicio medInicio = new MedInicio();
						medInicio.main(null);
					} else {
						JOptionPane.showMessageDialog(frame, "Erro na exclusão!");
					}
				}
			}
		});
		btnExcluir.setBounds(288, 335, 89, 23);
		frame.getContentPane().add(btnExcluir);
		
		// Bot�o Salvar
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
				
				if (MedicoController.editar(medico)) {
					JOptionPane.showMessageDialog(frame, "Médico alterado com sucesso!");
					alteraEdicao(false);
					btnSalvar.setVisible(false);
					btnEditar.setVisible(true);
					
				} else {
					System.out.println("Erro na inclusão!");
					JOptionPane.showMessageDialog(frame, "Erro na inclusão!");
				}
			}
		});
		btnSalvar.setBounds(388, 335, 89, 23);
		frame.getContentPane().add(btnSalvar);
		btnSalvar.setVisible(false);
		
		// Bot�o Editar
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditar.setVisible(false);
				btnSalvar.setVisible(true);
				btnVoltar.setText("Cancelar");
				alteraEdicao(true);
				editando = true;
			}
		});
		btnEditar.setBounds(388, 335, 89, 23);
		frame.getContentPane().add(btnEditar);
		
		// Bot�o Voltar
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editando) {
					alteraEdicao(false);
					editando = false;
					btnVoltar.setText("Voltar");
					btnEditar.setVisible(true);
					btnSalvar.setVisible(false);
				} else {
					frame.dispose();
					MedInicio.main(null);
				}
			}
		});
		btnVoltar.setBounds(488, 335, 89, 23);
		frame.getContentPane().add(btnVoltar);
		editando = false;
	}
	
}

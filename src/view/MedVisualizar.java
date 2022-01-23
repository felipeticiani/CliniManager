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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MedVisualizar {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textCrm;
	private JTextField textEspecialidade;
	private JTextField textEmail;
	private JPasswordField textSenha;
	private boolean editando;
	private String crmAtual;
	
	public void preencheDados(String[] medico) {
		textCrm.setText(medico[0]);
		textNome.setText(medico[1]);
		textTelefone.setText(medico[2]);
		textEmail.setText(medico[3]);
		textSenha.setText(medico[4]);
		textEspecialidade.setText(medico[5]);
	}
	
	public void alteraEdicao(boolean edicao) {
		textCrm.setEditable(edicao);
		textNome.setEditable(edicao);
		textTelefone.setEditable(edicao);
		textEmail.setEditable(edicao);
		textSenha.setEditable(edicao);
		textEspecialidade.setEditable(edicao);
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
						ArrayList<String[]> busca = MedicoController.consultar(med);
						String[] medico = busca.get(0);
						if (medico[1] == null) {
							JOptionPane.showMessageDialog(window.frame, "Nenhum médico encontrado!");
							window.frame.dispose();
							MedInicio.main(null);
						} else {
							window.preencheDados(medico);
							window.alteraEdicao(false);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						JOptionPane.showMessageDialog(window.frame, "Dados invlidos!");
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
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNome.setBounds(25, 60, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCrm = new JLabel("CRM");
		lblCrm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCrm.setBounds(25, 138, 46, 14);
		frame.getContentPane().add(lblCrm);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSenha.setBounds(25, 213, 46, 14);
		frame.getContentPane().add(lblSenha);
		
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
		
		textNome = new JTextField();
		textNome.setBounds(25, 84, 315, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setColumns(10);
		textTelefone.setBounds(378, 84, 199, 20);
		frame.getContentPane().add(textTelefone);
		
		textCrm = new JTextField();
		textCrm.setColumns(10);
		textCrm.setBounds(25, 163, 125, 20);
		frame.getContentPane().add(textCrm);
		
		textEspecialidade = new JTextField();
		textEspecialidade.setColumns(10);
		textEspecialidade.setBounds(188, 163, 152, 20);
		frame.getContentPane().add(textEspecialidade);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(378, 163, 199, 20);
		frame.getContentPane().add(textEmail);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(25, 238, 125, 20);
		frame.getContentPane().add(textSenha);
		
		// Variáveis para os botões
		JButton btnExcluir = new JButton("Excluir");
		JButton btnSalvar = new JButton("Salvar");
		JButton btnEditar = new JButton("Editar");
		JButton btnVoltar = new JButton("Voltar");
		
		// Botão Excluir
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opcoes = {"Cancelar", "Excluir"};
				int opcao = JOptionPane.showOptionDialog(btnExcluir, "Tem certeza que deseja excluir?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
				if (opcao == 1) {
					if (MedicoController.excluir(textCrm.getText())) {
						JOptionPane.showMessageDialog(frame, "Médico excluído com sucesso!");
						frame.dispose();
						MedInicio.main(null);
					} else {
						JOptionPane.showMessageDialog(frame, "Erro na exclusão!");
					}
				}
			}
		});
		btnExcluir.setBounds(288, 335, 89, 23);
		frame.getContentPane().add(btnExcluir);
		
		// Botão Salvar
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medico[] = new String[7];
				medico[0] = textNome.getText();
				medico[1] = textCrm.getText();
				medico[2] = textEspecialidade.getText();
				if (!textEmail.getText().isBlank())
					medico[3] = textEmail.getText();
				if (!textTelefone.getText().isBlank())
					medico[4] = textTelefone.getText();
				medico[5] = textSenha.getText();
				medico[6] = crmAtual;
				
				if (MedicoController.editar(medico)) {
					JOptionPane.showMessageDialog(frame, "Médico alterado com sucesso!");
					alteraEdicao(false);
					btnSalvar.setVisible(false);
					btnEditar.setVisible(true);
					editando = false;
					btnVoltar.setText("Voltar");
					
				} else {
					System.out.println("Erro na inclusão!");
					JOptionPane.showMessageDialog(frame, "Erro na inclusão!");
				}
			}
		});
		btnSalvar.setBounds(388, 335, 89, 23);
		frame.getContentPane().add(btnSalvar);
		btnSalvar.setVisible(false);
		
		// Botão Editar
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditar.setVisible(false);
				btnSalvar.setVisible(true);
				btnVoltar.setText("Cancelar");
				alteraEdicao(true);
				editando = true;
				crmAtual = textCrm.getText();
			}
		});
		btnEditar.setBounds(388, 335, 89, 23);
		frame.getContentPane().add(btnEditar);
		
		// Botão Voltar
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

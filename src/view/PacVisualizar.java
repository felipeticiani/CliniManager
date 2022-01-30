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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PacVisualizar {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textNascimento;
	private JTextField textEmail;
	private JTextField textEndereco;
	private JTextField textTelefone;
	private boolean editando;
	private String cpfAtual;
	
	public void preencheDados(String[] paciente) {
		textCpf.setText(paciente[0]);
		textNome.setText(paciente[1]);
		String[] dt = paciente[2].split("-");
		StringBuffer data = new StringBuffer(dt[2]);
		data.append("/" + dt[1] + "/" + dt[0]);
		textNascimento.setText(data.toString());
		textEmail.setText(paciente[3]);
		textEndereco.setText(paciente[4]);
		textTelefone.setText(paciente[5]);
		
	}
	
	public void alteraEdicao(boolean edicao) {
		textCpf.setEditable(edicao);
		textNome.setEditable(edicao);
		textTelefone.setEditable(edicao);
		textEmail.setEditable(edicao);
		textEndereco.setEditable(edicao);
		textNascimento.setEditable(edicao);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String pac) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacVisualizar window = new PacVisualizar();
					try {
						window.frame.setVisible(true);
						ArrayList<String[]> busca = PacienteController.consultar(pac);
						String[] paciente = busca.get(0);
						if (paciente[1] == null) {
							JOptionPane.showMessageDialog(window.frame, "Nenhum paciente encontrado!");
							window.frame.dispose();
							PacInicio.main(null);
						} else {
							window.preencheDados(paciente);
							window.alteraEdicao(false);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						JOptionPane.showMessageDialog(window.frame, "Dados inválidos!");
						window.frame.dispose();
						PacInicio.main(null);
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
	public PacVisualizar() {
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
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPaciente.setBounds(25, 24, 105, 14);
		frame.getContentPane().add(lblPaciente);
		
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
		lblEndereco.setBounds(25, 213, 60, 14);
		frame.getContentPane().add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefone.setBounds(378, 60, 60, 14);
		frame.getContentPane().add(lblTelefone);
		
		JLabel lblNascimento = new JLabel("Data de Nascimento");
		lblNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNascimento.setBounds(188, 138, 125, 14);
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
					if (PacienteController.excluir(textCpf.getText())) {
						JOptionPane.showMessageDialog(frame, "Paciente excluído com sucesso!");
						frame.dispose();
						PacInicio.main(null);
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
				String paciente[] = new String[7];
				paciente[0] = textNome.getText();
				paciente[1] = textCpf.getText();
				paciente[2] = textNascimento.getText();
				if (!textEmail.getText().isBlank())
					paciente[3] = textEmail.getText();
				if (!textEndereco.getText().isBlank())
					paciente[4] = textEndereco.getText();
				if (!textTelefone.getText().isBlank())
					paciente[5] = textTelefone.getText();
				paciente[6] = cpfAtual;
				
				if (PacienteController.editar(paciente)) {
					JOptionPane.showMessageDialog(frame, "Paciente alterado com sucesso!");
					alteraEdicao(false);
					btnSalvar.setVisible(false);
					btnEditar.setVisible(true);
					editando = false;
					btnVoltar.setText("Voltar");
					
				} else {
					System.out.println("Erro na inclusão!");
					JOptionPane.showMessageDialog(frame, "Erro na alteração!");
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
				cpfAtual = textCpf.getText();
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
					PacInicio.main(null);
				}
			}
		});
		btnVoltar.setBounds(488, 335, 89, 23);
		frame.getContentPane().add(btnVoltar);
		editando = false;
	}
	
}

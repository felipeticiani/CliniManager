package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.ConsultaController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ConCadastrar {

	// Variáveis
	private JFrame frame;
	private JTextField textPaciente;
	private JTextField textData;
	private JTextField textHorario;
	private JLabel lblPacienteSelecionado = new JLabel("");
	JComboBox<String> cbEspecialidade = new JComboBox<String>();
	JComboBox<String> cbMedico = new JComboBox<String>();
	String[] pac;
	ArrayList<String[]> m;
	
	// Botões
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnBuscar = new JButton("Buscar");
	JButton btnRemover = new JButton("Remover");
	JButton btnNovoPaciente = new JButton("Novo Paciente");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConCadastrar window = new ConCadastrar();
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
	public ConCadastrar() {
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
		
		JLabel lblNovo = new JLabel("Nova consulta");
		lblNovo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNovo.setBounds(25, 24, 125, 20);
		frame.getContentPane().add(lblNovo);
		
		// Texto Paciente Selecionado
		lblPacienteSelecionado.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPacienteSelecionado.setBounds(25, 87, 289, 14);
		frame.getContentPane().add(lblPacienteSelecionado);
		lblPacienteSelecionado.setVisible(false);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaciente.setBounds(25, 60, 46, 14);
		frame.getContentPane().add(lblPaciente);
		
		JLabel lblEspecialidade = new JLabel("Especialidade");
		lblEspecialidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEspecialidade.setBounds(25, 138, 85, 14);
		frame.getContentPane().add(lblEspecialidade);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblData.setBounds(25, 213, 72, 14);
		frame.getContentPane().add(lblData);
		
		JLabel lblMedico = new JLabel("Médico");
		lblMedico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMedico.setBounds(366, 138, 85, 14);
		frame.getContentPane().add(lblMedico);
		
		textPaciente = new JTextField();
		textPaciente.setBounds(25, 84, 315, 20);
		frame.getContentPane().add(textPaciente);
		textPaciente.setColumns(10);
		
		textData = new JTextField();
		textData.setBounds(25, 238, 125, 20);
		frame.getContentPane().add(textData);
		
		// Botão Salvar
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String consulta[] = new String[4];
				consulta[0] = textData.getText();
				consulta[1] = textHorario.getText();
				String[] medCrm = m.get(cbMedico.getSelectedIndex());
				consulta[2] = medCrm[0];
				consulta[3] = pac[0];
				
				if (ConsultaController.incluir(consulta)) {
					textPaciente.setText("");
					textData.setText("");
					textHorario.setText("");
					btnRemover.doClick();
					cbEspecialidade.setSelectedIndex(-1);
					cbMedico.setSelectedIndex(-1);
					JOptionPane.showMessageDialog(frame, "Consulta incluída com sucesso!");
					
				} else {
					System.out.println("Erro na inclusão!");
					JOptionPane.showMessageDialog(frame, "Erro na inclusão!");
				}
			}
		});
		btnSalvar.setBounds(366, 335, 89, 23);
		frame.getContentPane().add(btnSalvar);
		
		// Botão Cancelar
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConInicio.main(null);
			}
		});
		btnCancelar.setBounds(488, 335, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		// Campo Horário
		textHorario = new JTextField();
		textHorario.setBounds(215, 238, 125, 20);
		frame.getContentPane().add(textHorario);
		
		JLabel lblHorario = new JLabel("Horário");
		lblHorario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblHorario.setBounds(215, 213, 72, 14);
		frame.getContentPane().add(lblHorario);
		
		// ComboBox Especialidade
		cbEspecialidade.setBounds(25, 162, 315, 22);
		frame.getContentPane().add(cbEspecialidade);
		ArrayList<String> e = new ArrayList<String>(MedicoController.consultarEspecialidades());
		for(int i = 0; i < e.size(); i++) {
			cbEspecialidade.addItem(e.get(i));
		}
		cbEspecialidade.setSelectedIndex(-1);
		cbEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbEspecialidade.getSelectedIndex() < 0) {
					cbMedico.setSelectedIndex(-1);
				} else {
					cbMedico.removeAllItems();
					m = MedicoController.consultar(cbEspecialidade.getSelectedItem().toString());
					String[] med = null;
					for(int i = 0; i < m.size(); i++) {
						med = m.get(i);
						cbMedico.addItem(med[1]);
					}
					cbMedico.setSelectedIndex(-1);
				}
			}
		});
		
		// ComboBox Médico
		cbMedico.setBounds(366, 162, 211, 22);
		frame.getContentPane().add(cbMedico);
		cbMedico.setSelectedIndex(-1);
				
		// Botão Buscar
		btnBuscar.setBounds(366, 83, 89, 23);
		frame.getContentPane().add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> p = PacienteController.consultar(textPaciente.getText());
				if (p.size() == 0) {
					JOptionPane.showMessageDialog(frame, "Nenhum paciente encontrado!");
				} else if (p.size() == 1){
					textPaciente.setVisible(false);
					lblPacienteSelecionado.setVisible(true);
					btnBuscar.setVisible(false);
					btnRemover.setVisible(true);
					pac = p.get(0);
					lblPacienteSelecionado.setText(pac[1] + "  |  CPF: " + pac[0]);
				} else {
					JOptionPane.showMessageDialog(frame, "Mais de um paciente encontrado. Pesquise pelo CPF.");
				}
			}
		});
		
		// Botão Remover
		btnRemover.setBounds(366, 83, 89, 23);
		frame.getContentPane().add(btnRemover);
		btnRemover.setVisible(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscar.setVisible(true);
				btnRemover.setVisible(false);
				textPaciente.setVisible(true);
				lblPacienteSelecionado.setVisible(false);
				lblPacienteSelecionado.setText("");
				pac = null;
			}
		});
		
		// Botão Novo Paciente
		btnNovoPaciente.setBounds(465, 83, 112, 23);
		frame.getContentPane().add(btnNovoPaciente);
		btnNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacCadastrar.main(null);
			}
		});
	
	}
}

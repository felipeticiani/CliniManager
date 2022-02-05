package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.PacienteController;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PacInicio extends JFrame {

	private JFrame frame;
	private JTextField textBuscar;
	private JTable tblPacientes = new JTable();
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Windows".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					PacInicio window = new PacInicio();
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
	public PacInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("CliniManager");
		frame.setBounds(100, 100, 619, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblBuscarPaciente = new JLabel("Buscar Pacientes");
		lblBuscarPaciente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBuscarPaciente.setBounds(25, 48, 137, 14);
		frame.getContentPane().add(lblBuscarPaciente);
		
		JButton btnNovoPaciente = new JButton("Novo Paciente");
		btnNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PacCadastrar.main(null);
			}
		});
		btnNovoPaciente.setBounds(451, 146, 121, 23);
		frame.getContentPane().add(btnNovoPaciente);
		
		textBuscar = new JTextField();
		textBuscar.setToolTipText("");
		textBuscar.setBounds(25, 109, 395, 20);
		frame.getContentPane().add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textBuscar.getText().isBlank()) {
					model.setRowCount(0);
					ArrayList<String[]> pacientes = new ArrayList<String[]>();
					pacientes = PacienteController.consultar(textBuscar.getText());
					for (int i = 0; i < pacientes.size(); i++) {
						String[] paciente = pacientes.get(i);
						Object[] celula = {paciente[1], paciente[0]};
						model.addRow(celula);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Informe um dado para buscar!");
				}
			}
		});
		btnBuscar.setBounds(451, 108, 121, 23);
		frame.getContentPane().add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("Pesquise por nome ou CPF.");
		lblBuscar.setBounds(25, 89, 251, 14);
		frame.getContentPane().add(lblBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 146, 395, 252);
		frame.getContentPane().add(scrollPane);
		tblPacientes.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent clique) {
		        if (clique.getClickCount() == 2 && tblPacientes.getSelectedRow() != -1) {
		            int cpf = tblPacientes.getSelectedRow();
		            frame.dispose();
		            String envio = (String) tblPacientes.getValueAt(cpf, 1);
					PacVisualizar.main(envio);
		        }
		    }
		});
		
		// Tabela
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Object[] coluna = {"Paciente", "CPF"};
		model.setColumnIdentifiers(coluna);
		tblPacientes.setModel(model);
		tblPacientes.getColumnModel().getColumn(0).setPreferredWidth(225);
		tblPacientes.getColumnModel().getColumn(0).setMinWidth(150);
		tblPacientes.getColumnModel().getColumn(0).setMaxWidth(400);
		tblPacientes.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblPacientes.getColumnModel().getColumn(1).setMinWidth(75);
		tblPacientes.getColumnModel().getColumn(1).setMaxWidth(150);
		tblPacientes.setDefaultEditor(Object.class, null);
		tblPacientes.setRowHeight(25);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 603, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmConInicio = new JMenuItem("Início");
		mntmConInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConInicio.main(null);
			}
		});
		mnConsultas.add(mntmConInicio);
		
		JMenuItem mntmConCadastrar = new JMenuItem("Nova consulta");
		mnConsultas.add(mntmConCadastrar);
		
		JMenu mnPacientes = new JMenu("Pacientes");
		menuBar.add(mnPacientes);
		
		JMenuItem mntmPacBuscar = new JMenuItem("Buscar");
		mnPacientes.add(mntmPacBuscar);
		
		JMenuItem mntmPacCadastrar = new JMenuItem("Novo paciente");
		mntmPacCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PacCadastrar.main(null);
			}
		});
		mnPacientes.add(mntmPacCadastrar);
		
		JMenu mnMédicos = new JMenu("Médicos");
		menuBar.add(mnMédicos);
		
		JMenuItem mntmMedBuscar = new JMenuItem("Buscar");
		mntmMedBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MedInicio.main(null);
			}
		});
		mnMédicos.add(mntmMedBuscar);
		
		JMenuItem mntmMedCadastrar = new JMenuItem("Novo médico");
		mntmMedCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MedCadastrar.main(null);
			}
		});
		mnMédicos.add(mntmMedCadastrar);
	}
}

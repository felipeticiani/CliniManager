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

import controller.ConsultaController;
import controller.MedicoController;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class ConInicio extends JFrame {

	private JFrame frame;
	private JTable tblConsultas = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	private JTextField textDe;
	private JTextField textAte;
	
	// Botões
	JButton btnHoje = new JButton("Hoje");
	JButton btnFiltrar = new JButton("Filtrar");
	JButton btnBuscar = new JButton("Nova consulta");
	
	String[] filtros = new String[]{"", ""};
	
	public void preencheDados() {
		model.setRowCount(0);
		ArrayList<String[]> consultas = new ArrayList<String[]>();
		consultas = ConsultaController.consultar(filtros);
		for (int i = 0; i < consultas.size(); i++) {
			String[] cons = consultas.get(i);
			Object[] celula = {cons[0], cons[1], cons[3], cons[2]};
			model.addRow(celula);
		}
	}

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
					ConInicio window = new ConInicio();
					window.frame.setVisible(true);
					window.preencheDados();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConInicio() {
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
		
		JLabel lblConsultas = new JLabel("Consultas");
		lblConsultas.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblConsultas.setBounds(25, 48, 89, 14);
		frame.getContentPane().add(lblConsultas);
		
		// Botão Nova Consulta
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBuscar.setBounds(451, 108, 121, 23);
		frame.getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 108, 395, 290);
		frame.getContentPane().add(scrollPane);
		tblConsultas.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent clique) {
		        //if (clique.getClickCount() == 2 && tblConsultas.getSelectedRow() != -1) {
		        //    int cpf = tblConsultas.getSelectedRow();
		        //    frame.dispose();
		        //    String envio = (String) tblConsultas.getValueAt(cpf, 1);
				//	PacVisualizar.main(envio);
		        //}
		    }
		});
		
		// Tabela
		scrollPane.setViewportView(tblConsultas);
		tblConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Object[] coluna = {"Data","Horário", "Paciente", "Médico"};
		model.setColumnIdentifiers(coluna);
		tblConsultas.setModel(model);
		tblConsultas.getColumnModel().getColumn(0).setPreferredWidth(65);
		tblConsultas.getColumnModel().getColumn(0).setMinWidth(65);
		tblConsultas.getColumnModel().getColumn(0).setMaxWidth(65);
		tblConsultas.getColumnModel().getColumn(1).setPreferredWidth(45);
		tblConsultas.getColumnModel().getColumn(1).setMinWidth(45);
		tblConsultas.getColumnModel().getColumn(1).setMaxWidth(45);
		tblConsultas.getColumnModel().getColumn(2).setPreferredWidth(165);
		tblConsultas.getColumnModel().getColumn(2).setMinWidth(165);
		tblConsultas.getColumnModel().getColumn(2).setMaxWidth(200);
		tblConsultas.getColumnModel().getColumn(3).setPreferredWidth(165);
		tblConsultas.getColumnModel().getColumn(3).setMinWidth(165);
		tblConsultas.getColumnModel().getColumn(3).setMaxWidth(200);
		tblConsultas.setDefaultEditor(Object.class, null);
		tblConsultas.setRowHeight(25);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 603, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmConInicio = new JMenuItem("Início");
		mnConsultas.add(mntmConInicio);
		
		JMenuItem mntmConCadastrar = new JMenuItem("Nova consulta");
		mnConsultas.add(mntmConCadastrar);
		
		JMenu mnPacientes = new JMenu("Pacientes");
		menuBar.add(mnPacientes);
		
		JMenuItem mntmPacBuscar = new JMenuItem("Buscar");
		mntmPacBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PacInicio.main(null);
			}
		});
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
		
		JLabel lblFiltrar = new JLabel("Filtrar por período:");
		lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFiltrar.setBounds(451, 142, 121, 14);
		frame.getContentPane().add(lblFiltrar);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(451, 167, 24, 20);
		frame.getContentPane().add(lblDe);
		
		textDe = new JTextField();
		textDe.setBounds(483, 167, 89, 20);
		frame.getContentPane().add(textDe);
		textDe.setColumns(10);
		
		textAte = new JTextField();
		textAte.setColumns(10);
		textAte.setBounds(483, 198, 89, 20);
		frame.getContentPane().add(textAte);
		
		JLabel lblAte = new JLabel("Até");
		lblAte.setBounds(451, 198, 24, 20);
		frame.getContentPane().add(lblAte);
		
		// Botão Filtrar
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros[0] = textDe.getText();
				filtros[1] = textAte.getText();
				preencheDados();
				btnHoje.setEnabled(true);
			}
		});
		btnFiltrar.setBounds(511, 229, 61, 23);
		frame.getContentPane().add(btnFiltrar);
		
		// Botão Hoje
		btnHoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros[0] = "";
				filtros[1] = "";
				textDe.setText(filtros[0]);
				textAte.setText(filtros[1]);
				preencheDados();
				btnHoje.setEnabled(false);
			}
		});
		btnHoje.setBounds(451, 229, 61, 23);
		frame.getContentPane().add(btnHoje);
	}
}

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.MedicoController;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedInicio extends JFrame {

	private JFrame frame;
	private JTextField textBuscar;
	private JTable tblMedicos = new JTable();
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
					MedInicio window = new MedInicio();
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
	public MedInicio() {
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
		
		
		JLabel lblBuscarMedico = new JLabel("Buscar Médicos");
		lblBuscarMedico.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBuscarMedico.setBounds(25, 24, 137, 14);
		frame.getContentPane().add(lblBuscarMedico);
		
		JButton btnNewButton = new JButton("Novo Médico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MedCadastrar.main(null);
			}
		});
		btnNewButton.setBounds(451, 122, 121, 23);
		frame.getContentPane().add(btnNewButton);
		
		textBuscar = new JTextField();
		textBuscar.setToolTipText("");
		textBuscar.setBounds(25, 85, 395, 20);
		frame.getContentPane().add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textBuscar.getText().isBlank()) {
					model.setRowCount(0);
					ArrayList<String[]> medicos = new ArrayList<String[]>();
					medicos = MedicoController.consultar(textBuscar.getText());
					for (int i = 0; i < medicos.size(); i++) {
						String[] medico = medicos.get(i);
						Object[] celula = {medico[1], medico[0]};
						model.addRow(celula);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Informe um dado para buscar!");
				}
			}
		});
		btnBuscar.setBounds(451, 84, 121, 23);
		frame.getContentPane().add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("Pesquise por nome, CRM ou especialidade.");
		lblBuscar.setBounds(25, 65, 251, 14);
		frame.getContentPane().add(lblBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 122, 395, 252);
		frame.getContentPane().add(scrollPane);
		tblMedicos.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent clique) {
		        if (clique.getClickCount() == 2 && tblMedicos.getSelectedRow() != -1) {
		            int crm = tblMedicos.getSelectedRow();
		            frame.dispose();
		            String envio = (String) tblMedicos.getValueAt(crm, 1);
					MedVisualizar.main(envio);
		        }
		    }
		});
		
		// Tabela
		scrollPane.setViewportView(tblMedicos);
		tblMedicos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Object[] coluna = {"Médico", "CRM"};
		model.setColumnIdentifiers(coluna);
		tblMedicos.setModel(model);
		tblMedicos.getColumnModel().getColumn(0).setPreferredWidth(225);
		tblMedicos.getColumnModel().getColumn(0).setMinWidth(150);
		tblMedicos.getColumnModel().getColumn(0).setMaxWidth(400);
		tblMedicos.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblMedicos.getColumnModel().getColumn(1).setMinWidth(75);
		tblMedicos.getColumnModel().getColumn(1).setMaxWidth(150);
		tblMedicos.setDefaultEditor(Object.class, null);
		tblMedicos.setRowHeight(25);
	}
}

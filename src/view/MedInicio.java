package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import controller.MedicoController;

public class MedInicio extends JFrame {

	private JFrame frame;
	private JTextField textNomeBuscar;
	private JTextField textCrmBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		JLabel lblNomeBuscar = new JLabel("Nome");
		lblNomeBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNomeBuscar.setBounds(25, 66, 46, 14);
		frame.getContentPane().add(lblNomeBuscar);
		
		JButton btnNewButton = new JButton("Novo Médico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MedCadastrar.main(null);
			}
		});
		btnNewButton.setBounds(451, 111, 121, 23);
		frame.getContentPane().add(btnNewButton);
		
		textNomeBuscar = new JTextField();
		textNomeBuscar.setToolTipText("");
		textNomeBuscar.setBounds(78, 64, 342, 20);
		frame.getContentPane().add(textNomeBuscar);
		textNomeBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textCrmBuscar.getText().isBlank()) {
					frame.dispose();
					MedVisualizar.main(textCrmBuscar.getText());
				} else if (!textNomeBuscar.getText().isBlank()) {
					frame.dispose();
					MedVisualizar.main(textNomeBuscar.getText());
				} else {
					JOptionPane.showMessageDialog(frame, "Informe um dado para buscar!");
				}
			}
		});
		btnBuscar.setBounds(451, 63, 121, 23);
		frame.getContentPane().add(btnBuscar);
		
		JLabel lblCrmBuscar = new JLabel("CRM");
		lblCrmBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCrmBuscar.setBounds(25, 114, 46, 14);
		frame.getContentPane().add(lblCrmBuscar);
		
		textCrmBuscar = new JTextField();
		textCrmBuscar.setToolTipText("");
		textCrmBuscar.setColumns(10);
		textCrmBuscar.setBounds(78, 112, 342, 20);
		frame.getContentPane().add(textCrmBuscar);
	}
}

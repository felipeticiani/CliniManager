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
	private JTextField textBuscar;

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
		
		JButton btnNewButton = new JButton("Novo Médico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MedCadastrar.main(null);
			}
		});
		btnNewButton.setBounds(451, 132, 121, 23);
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
					frame.dispose();
					MedVisualizar.main(textBuscar.getText());
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
	}
}

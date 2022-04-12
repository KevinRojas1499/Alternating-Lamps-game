import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*;


public class MenuPrincipal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ANCHO = 700;
	private final int LARGO = 700;	
	
	private JButton Jugador1,Jugador2,salir;
	private String[] tablero = new String[26];
	@SuppressWarnings("rawtypes")
	private JComboBox tab;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPrincipal() {
		super("Juego de las lamparas");
		for(int i = 0; i < 26; i++) {
			tablero[i] = ""+(i+1);
		}
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		this.setLayout(null);
		setSize(ANCHO,LARGO);
		setResizable(false);
		tab = new JComboBox(tablero);
		
		panel.add(tab);
		Jugador1 = new JButton("JugarMario");
		panel.add(Jugador1);
		Jugador2 = new JButton("JugarDibujo");
		panel.add(Jugador2);
		salir = new JButton("Salir");
		panel.add(salir);
		
		this.add(panel);
		
		panel.setBounds(270,300,125,100);
		ManejadorBoton manejador = new ManejadorBoton();
		Jugador1.addActionListener(manejador);
		Jugador2.addActionListener(manejador);
		salir.addActionListener(manejador);
		setVisible(true);

		setResizable(false);
		setVisible(true);
	}	
	
	private class ManejadorBoton implements ActionListener{
		public void actionPerformed(ActionEvent evento) {
			if("JugarMario".equals(evento.getActionCommand())) {
				int tamano = (int)(tab.getSelectedIndex()+1);
				new Juego(tamano,0);
				setVisible(false);
				dispose();
			}
			else if("JugarDibujo".equals(evento.getActionCommand())) {
				int tamano = (int)(tab.getSelectedIndex()+1);
				new Juego(tamano,1);
				setVisible(false);
				dispose();
			}
			else if("Salir".equals(evento.getActionCommand())) {
				setVisible(false);
				dispose();
			}			
		}
	}	

	public void paint (Graphics g) {
		super.paint(g);
		g.setFont(new Font("Monospaced",Font.BOLD,50));
		g.drawString("Juego de las Lamparas", 25, 200);
	}

	public static void main (String args []) {
		new MenuPrincipal();
	}
}

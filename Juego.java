import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Juego{
	private Tablero tab;
	private JButton Atras,salir;
	private Frame fr;

	
	public Juego(int tamano,int dibujo){
		fr = new Frame("Longleat");
		JPanel panel = new JPanel();	
		panel.setLayout(new GridLayout(2,1));
		Atras = new JButton("Volver");
		panel.add(Atras);
		salir = new JButton("Salir");
		panel.add(salir);
		fr.add(panel);
		panel.setBounds(580,600,100,50);
		tab = new Tablero(tamano,dibujo);
		fr.add(tab);
		
		ManejadorBoton manejador = new ManejadorBoton();
		Atras.addActionListener(manejador);
		salir.addActionListener(manejador);

		fr.setSize(700,700);
		fr.setResizable(false);
		fr.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				e.getWindow().dispose();
			}
		});
		tab.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){

					Tablero tab = (Tablero) e.getSource();
					int ladoCuadro = Tablero.ANCHO / tab.getTamano();
					int x = e.getX();
					int y = e.getY();
					int columna = (x-Tablero.POSX)/ladoCuadro;
					int fila = (y-Tablero.POSY)/ladoCuadro;				
					tab.mover(fila, columna);
					tab.repaint();		
			}
		});
		fr.setVisible(true);
	}
	
	private class ManejadorBoton implements ActionListener{
		public void actionPerformed(ActionEvent evento) {
			if("Volver".equals(evento.getActionCommand())) {
				new MenuPrincipal();
				fr.setVisible(false);
				fr.dispose();

			}
			else if("Salir".equals(evento.getActionCommand())) {
				fr.setVisible(false);
				fr.dispose();
			}			
		}
	}
	/**
	 * @brief Pinta el tablero a la pantalla
	 */
	public void paint(Graphics g) {
		tab.repaint();
	}
	
	/**
	 * @brief Actualiza los puntajes de ambos jugadores.
	 */
	

}

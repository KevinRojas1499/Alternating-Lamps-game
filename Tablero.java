import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @brief Objeto que contiene los datos del juego y se dibuja a sí mismo.
 * 
 * Este objeto es una extensión de Canvas que se dibuja solo y contiene
 * toda la información del juego.
 * 
 */
public class Tablero extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamano;
	private int dibujo;
	private int [][] lamparas;
	private BufferedImage bufferSegundoPlano;

	public static final int POSX = 15;
	public static final int POSY = 110;
	public static final int ANCHO = 551;
	public static final int ALTO = 551;
	
	public Tablero(int tamano,int dib){
		this.dibujo = dib;
		this.tamano = tamano;
		lamparas = new int[tamano][tamano];
		for(int i = 0; i < tamano; i++) {
			for(int j = 0; j < tamano; j++) {
				lamparas[i][j] = 0;
			}
		}
	}

	private void pintarCuadros(Graphics g){
		int ladoCuadro = ANCHO/tamano;
		Color color1 = Color.WHITE, color2 = Color.GRAY;
		g.setColor(color1);
		g.fillRect(0,0,ladoCuadro*tamano, ladoCuadro*tamano);
		g.setColor(color2);
		for(int i = 0; i < tamano; i++){
			for(int j = 0; j < tamano; j++){
				if((i+j)%2==1){
					g.fillRect(i*ladoCuadro, j*ladoCuadro, ladoCuadro, ladoCuadro);
				}
			}
		}
		
	}
	
	private void pintarFicha(Graphics g){
		int ladoCuadro = ANCHO/tamano;
		Color ficha = Color.RED;
		g.setColor(ficha);
		for(int i = 0; i < tamano; i++){
			for(int j = 0; j < tamano; j++){
				if(lamparas[i][j] == 0){
					if(dibujo == 0) {
						Image img = null;
						try {
							img = ImageIO.read( new File("Mario1.png" ));
						} catch (IOException e) {
							e.printStackTrace();
						}
						img = img.getScaledInstance(ladoCuadro/2, ladoCuadro*72/37/2, Image.SCALE_DEFAULT);
						g.drawImage( img, i*ladoCuadro+ladoCuadro/4, j*ladoCuadro, this );
					}
					if(dibujo == 1) {
						g.setColor(Color.BLACK);
						g.fillOval(i*ladoCuadro+ladoCuadro/4, j*ladoCuadro+ladoCuadro/4, ladoCuadro/2, ladoCuadro/2);
					}
					

				}
				else {
					if(dibujo == 0) {
						Image img = null;
						try {
							img = ImageIO.read( new File("Mario3.png" ));
						} catch (IOException e) {
							e.printStackTrace();
						}
						img = img.getScaledInstance(ladoCuadro/2, ladoCuadro*72/37/2, Image.SCALE_DEFAULT);
						g.drawImage( img, i*ladoCuadro+ladoCuadro/4, j*ladoCuadro, this );
					}
					if(dibujo == 1) {
						g.setColor(new Color(220,20,60));
						g.fillOval(i*ladoCuadro+ladoCuadro/4, j*ladoCuadro+ladoCuadro/4, ladoCuadro/2, ladoCuadro/2);
						g.setColor(Color.black);
						g.drawOval(i*ladoCuadro+ladoCuadro/4, j*ladoCuadro+ladoCuadro/4, ladoCuadro/2, ladoCuadro/2);
					}

				}
			}
		}

	}
	
	private void pintarBorde(Graphics g) {
		int lado = ANCHO/tamano;
		for(int i  = 0; i <= tamano; i++) {
			g.setColor(Color.black);
			g.drawLine(0, i*lado, tamano*lado, i*lado);	
			g.drawLine(i*lado, 0, i*lado, tamano*lado);	
		}

	}
	
	public void paint(Graphics g){
		if(bufferSegundoPlano==null)
			bufferSegundoPlano = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_ARGB);
		Graphics imagenGraficos = bufferSegundoPlano.getGraphics();
		pintarCuadros(imagenGraficos);
		pintarFicha(imagenGraficos);
		pintarBorde(imagenGraficos);
		g.drawImage(bufferSegundoPlano, POSX ,POSY , null);
	}

	public void update(Graphics g){
		paint(g);
	}

	public void mover(int m, int n){
		int n1 = tamano;
		for(int i = 0; i < n1;i++) {
			if(i!=n)
				lamparas[i][m] = lamparas[i][m] == 1 ? 0:1;
			lamparas[n][i] = lamparas[n][i] == 1 ? 0:1;
		}
	}
	

	
	/**
	 * @brief Devuelve el tamaño del tablero.
	 * @return Un entero con el tamaño del tablero.
	 */
	public int getTamano(){
		return tamano;
	}
	

}

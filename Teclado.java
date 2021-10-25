package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];

	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;

	public void actualizar() // fijamoms el valor arriba tenga el booleano que se guarda en teclas
	{
		arriba = teclas[KeyEvent.VK_W]; // la variable arriba equivale a pulsar la tecla "W"
		abajo = teclas[KeyEvent.VK_S]; // la variable abajo equivale a pulsar la tecla "S"
		izquierda = teclas[KeyEvent.VK_A]; // la variable izquierda equivale la tecla "A"
		derecha = teclas[KeyEvent.VK_D]; // la variable derecha equivale la tecla "D"
	}

	public void keyPressed(KeyEvent e) { // cuando pulsamos una tecla y no la hemos soltado
		teclas[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) { // cuando soltamos la tecla cuando la habiamos soltado
		teclas[e.getKeyCode()] = false;

	}

	public void keyTyped(KeyEvent e) { // tecla tecleada, pulsa y la soltamos

	}

}

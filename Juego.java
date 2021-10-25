package juego;

import java.awt.BorderLayout;
import java.awt.Canvas; //importando la clase canvas del paquete awt
import java.awt.Dimension;

import javax.swing.JFrame;

import control.Teclado;

public class Juego extends Canvas implements Runnable { // creacion de canvas en la ventana
	// con la interfaz Runnable se utilizan nuevos Threads y debemos crear un vois
	// run(){}

	private static final long serialVersionUID = 1L;// cracion de identificador. Sirve por si guardamos la calse y la
													// volvemos a utilizar y ha cambiado

	private static final int ANCHO = 800;// final porque no va a cambiar el tamaño y al escirbiir esto sera mas rapido
	private static final int ALTO = 600;

	private static volatile boolean enFuncionamiento = false; // volatile sirve para que la variable no se use a la vez
																// por los dos threads y no se cuelgue el programa

	private static final String NAME = "JUEGO";

	private static int aps = 0;
	private static int fps = 0;

	private static JFrame ventana; // ventana que utiliza java (JF)
	private static Thread thread; // segundo Thread
	private static Teclado teclado;

	private Juego() { // Solo esta calse podra escibir estancias de juego

		// constructor
		setPreferredSize(new Dimension(ANCHO, ALTO));

		teclado = new Teclado();
		addKeyListener(teclado); // detecta las teclas pulsadas en esta clase, todo lo que se este pulsando
									// dentro del canvas

		// inicimaos nuestro objeto ventana
		ventana = new JFrame(NAME); // nuestra ventana tendra como nombre= "Juego"
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // se cierra la aplicacion para que no consuma recursos
																// en 2o plano. Se cierra todo
		ventana.setResizable(false); // El usuario no podra cambiar el tamaño de la ventana, maximizarla,
										// minimizarla... etc.
		ventana.setLayout(new BorderLayout());// organizacion interna de la ventana BorderLayout es el gestor.
		ventana.add(this, BorderLayout.CENTER); // añadimos a la ventana el canvas. El center pone el canvas en el
												// centro de la ventana.
		ventana.pack(); // todo el contenido de la ventana se ajuste al tamaño 800x600. Si no se pone
						// puede aparecer una pantalla muy pequeña
		ventana.setLocationRelativeTo(null); // fija la ventana en el centro del escritorio.
		ventana.setVisible(true); // con esto se puede ver la ventana en el ordenador

	}

	// metodo main
	public static void main(String[] args) { // siempre debe ser public sino no podra verse nada.
		Juego juego = new Juego(); // instancia de nuestra clase. Ya podemos ver la ventana
		juego.iniciar();

	}

	private synchronized void iniciar() { // synchronized es la palabra clave, es como volatile
		// donde se iniciara el segundo Thread o juego en general
		// creamos el Thread
		enFuncionamiento = true;

		thread = new Thread(this, "graficos");
		thread.start(); // inicia el thread, lo pone en ejecucion, todo lo que este dentro de las llaves
						// de run estara ejecutandose de forma secuencial.

	}

	private synchronized void detener() {
		// metodo detener
		enFuncionamiento = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void actualizar() {// metodo que se ejecuta 60 veces por segundo

		// primero ejecutamos en actualizar el teclado para que vaya comprobando el
		// teclado que se pulsa.
		teclado.actualizar();

		if (teclado.arriba) {
			System.out.println("Arriba");
		}
		if (teclado.abajo) {
			System.out.println("Abajo");
		}
		if (teclado.izquierda) {
			System.out.println("izquierda");
		}
		if (teclado.derecha) {
			System.out.println("Derecha");
		}

		aps++;
	}

	private void mostrar() {
		fps++;
	}

	public void run() {
		// donde correra el segundo Thread
		// Temporizador:
		final int NS_POR_SEGUNDO = 1000000000; // un segundo en nanosegundos
		final byte APS_OBJETIVO = 60; // actualizaciones por segundo
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; // calculo de nanoseagundos por actualizacion

		long referenciaActualizacion = System.nanoTime(); // se le atribuira una cantidad de nanosegundos en ese momento
															// exacto
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0; // tiempo que transcurre hasta que se realiza una actualizacion

		requestFocus(); // Nuestra pantalla de la aplicacion tendra el foco, donde solamente en nuestra
						// pantalla se pueda usar el teclado y el ususario no tenga que estar cliceando
						// en el canvas para deifinir el foco

		while (enFuncionamiento) {

			final long inicioBucle = System.nanoTime(); // iniciamos el "cronometro"

			tiempoTranscurrido = inicioBucle - referenciaActualizacion; // tiempo transcurrido ente el inicioBucle y
																		// referenciaActu
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION; // sumamos a delta el resultado de dividir el tiempo
																// transcurido ente nano segundos por actualiz.
			while (delta >= 1) { // bucle anidado y se ejecuta solo si delta es mayor o igual a 1 y suele llegar
									// o pasar.
				actualizar();// cuando sea 1, se actualiza delta
				delta--;// se resta y despues inicia el bucle una y otra vez
			}

			mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				// esto hace que nuestro contador se actualice cada segundo
				ventana.setTitle(NAME + " || APS: " + aps + " || FPS: " + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}

	}

}
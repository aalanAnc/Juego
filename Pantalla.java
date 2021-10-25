package graficos;

public class Pantalla {

	private final int ancho;
	private final int alto;

	public final int[] pixeles;

	public Pantalla(final int ancho, final int alto) {// inicializamos valores
		this.ancho = ancho;
		this.alto = alto;
		pixeles = new int[alto * ancho];

	}

	public void limpiar() { // pintar todos los pixeles del array de negro

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0; // 0 es el color negro
		}

	}

	public void mostrar(final int compensacionX, final int compensacionY) {
		// Es el movimiento que nosotros estemos realizando con wasd, es el movimiento
		// del personaje

		for (int y = 0; y < alto; y++) {// dibujamos las lineas de Y
			// calculamos la posicion exacta que queremos dibujar
			int posicionY = y + compensacionY;
			if (posicionY < 0 || posicionY >= alto) { // para que no nos salgamos del mapa, no podemos dibujar lo que
														// esta fuera.
				continue; // nos salimos del bucle for
			}

			for (int x = 0; x < ancho; x++) {// dibujamos las lineas de X
				int posicionX = x + compensacionX;
				if (posicionX < 0 || posicionX >= ancho) {
					continue;
				}
				// los if son para ver que no nos salgamos de la pantalla.
			}
		}
	}

}

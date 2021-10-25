package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int[] pixeles; // array o arreglo

	public HojaSprites(final String ruta, final int ancho, final int alto) {
		// final es que el valor no va a cambiar de esa manera durante todo el
		// constructor, no va a variar

		this.ancho = ancho; // cuando ponemos un this. delante de un valor significa que es el propio de la
							// calse, si no hay this es del constructor o metodo
		this.alto = alto;

		pixeles = new int[ancho * alto]; // inicializamos el array
		// el array tendra el mismo tamaño que pixeles tenga nuestra imagen

		BufferedImage imagen;

		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta)); // Hemos creado una imagen y le hemos dado el
																		// valor de una ruta
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public int obtenAncho() {
		return ancho;
	}
}

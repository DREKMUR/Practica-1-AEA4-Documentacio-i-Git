package documentacio_git;

/**
 * Representa una categoria o tipus de classificació per als materials de l'inventari.
 * Serveix per agrupar materials amb característiques similars.
 */
public class Categoria {
	private String code;
	private String name;
	private String description;

	/**
	 * Constructor per crear una nova categoria.
	 * * @param code Codi únic abreviat de la categoria.
	 * @param name Nom complet de la categoria.
	 * @param description Descripció detallada del tipus de materials que inclou.
	 */
	public Categoria(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	/**
	 * Obté el nom de la categoria.
	 * @return El nom de la categoria.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obté la descripció de la categoria.
	 * @return La descripció textual.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Obté el codi identificador de la categoria.
	 * @return El codi de la categoria.
	 */
	public String getCode() {
		return code;
	}
}
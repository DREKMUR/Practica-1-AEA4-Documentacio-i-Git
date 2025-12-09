package documentacio_git;

/**
 * Representa un ítem físic (material) registrat dins del sistema d'inventari.
 * Conté la informació bàsica i l'estat actual de l'objecte.
 */
public class Material {
    private int id;
    private String name;
    private int serialNumber;
    private MaterialStates state;
    private Categoria category;
    
    /**
     * Crea un nou material amb l'estat inicial DISPONIBLE.
     * * @param id Identificador únic del material.
     * @param name Nom descriptiu del material.
     * @param serialNumber Número de sèrie del fabricant.
     */
    public Material(int id, String name, int serialNumber){
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.state = MaterialStates.DISPONIBLE;
    }

    /**
     * Obté l'estat actual del material.
     * @return L'enum corresponent a l'estat actual (DISPONIBLE, PRESTADO, etc.).
     */
    public MaterialStates getState() {
        return state;
    }

    /**
     * Modifica l'estat del material.
     * @param state El nou estat a assignar.
     */
    public void setState(MaterialStates state) {
        this.state = state;
    }

    /**
     * Obté la categoria assignada a aquest material.
     * @return L'objecte Categoria associat.
     */
    public Categoria getCategory() {
        return category;
    }

    /**
     * Assigna una categoria al material.
     * @param category La categoria a assignar.
     */
    public void setCategory(Categoria category) {
        this.category = category;
    }

    /**
     * Obté l'identificador del material.
     * @return L'ID numèric del material.
     */
    public int getId() {
        return id;
    }

    /**
     * Obté el nom del material.
     * @return El nom del material.
     */
    public String getName() {
        return name;
    }

    /**
     * Obté el número de sèrie del material.
     * @return El número de sèrie.
     */
    public int getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * Comprova si el material està disponible per ser prestat.
     * * @return true si l'estat és DISPONIBLE, false en cas contrari.
     */
    public boolean isAvailable() {
        return this.state == MaterialStates.DISPONIBLE;
    }
}
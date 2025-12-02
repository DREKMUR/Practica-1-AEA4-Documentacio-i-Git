package documentacio_git;

import java.util.List;

/**
 * Servei principal per a la gestió de l'inventari.
 * <p>
 * Aquesta classe actua com a intermediari (controlador) entre la interfície d'usuari
 * i el repositori de dades, contenint tota la lògica de negoci.
 * </p>
 */
public class InventariService {
    
    private InventariRepository repository;

    /**
     * Constructor de la classe. Inicialitza la connexió amb el repositori.
     */
    public InventariService() {
        this.repository = new InventariRepository();
    }

    /**
     * Crea un nou material i l'afegeix al repositori de l'inventari.
     * * @param id Identificador únic del material.
     * @param name Nom descriptiu del material.
     * @param serialNumber Número de sèrie del fabricant.
     */
    public void addNewMaterial(int id, String name, int serialNumber) {
        Material m = new Material(id, name, serialNumber);
        repository.saveMaterial(m);
    }

    /**
     * Gestiona el procés de préstec d'un material a un usuari.
     * <p>
     * Verifica si el material existeix i està disponible. Si és així, canvia el seu
     * estat a PRESTADO, crea el registre del préstec i el guarda.
     * </p>
     * * @param user El nom de l'usuari que demana el préstec.
     * @param materialId L'identificador del material a prestar.
     * @return true si el préstec s'ha fet correctament, false si no s'ha pogut fer.
     */
    public boolean registerPrestec(String user, int materialId) {
        Material m = repository.findMaterialById(materialId);
        
        if (m != null && m.getState() == MaterialStates.DISPONIBLE) {
            m.setState(MaterialStates.PRESTADO);
            Prestec p = new Prestec(m, user);
            repository.savePrestec(p);
            return true;
        }
        return false;
    }

    /**
     * Processa la devolució d'un material prestat.
     * <p>
     * Cerca el material, actualitza el seu estat a DISPONIBLE i tanca el préstec
     * actiu associat a aquest material.
     * </p>
     * * @param materialId Identificador del material que es retorna.
     */
    public void returnMaterial(int materialId) {
        Material m = repository.findMaterialById(materialId);
        if (m != null) {
            m.setState(MaterialStates.DISPONIBLE);
            
            List<Prestec> history = repository.getAllPrestecs();
            for (Prestec p : history) {
                if (p.material.getId() == materialId && p.active) {
                    p.endPrestec();
                }
            }
        }
    }

    /**
     * Obté una llista amb tots els materials que actualment estan disponibles.
     * * @return Llista d'objectes Material amb estat DISPONIBLE.
     */
    public List<Material> getAvailableMaterials() {
        return repository.findMaterialsByState(MaterialStates.DISPONIBLE);
    }

    /**
     * Compta el nombre total de materials registrats a l'inventari.
     * * @return El nombre total d'ítems (int).
     */
    public int countStockTotal() {
        return repository.getAllMaterials().size();
    }
}
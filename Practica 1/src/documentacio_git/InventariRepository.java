package documentacio_git;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositori de dades que simula una base de dades en memòria.
 * Emmagatzema les llistes de materials i l'historial de préstecs mentre l'aplicació s'executa.
 */
public class InventariRepository {
    
    /** Llista estàtica que conté tots els materials del sistema. */
    public static List<Material> materialsList = new ArrayList<>();
    
    /** Llista estàtica que conté l'historial de tots els préstecs realitzats. */
    public static List<Prestec> prestecsHistory = new ArrayList<>();

    /**
     * Constructor per defecte del repositori.
     * Inicialitza les estructures de dades si és necessari.
     */
    public InventariRepository() {
        // Constructor buit explícit per a Javadoc
    }

    /**
     * Guarda un nou material a la llista de materials.
     * @param m L'objecte Material a guardar.
     */
    public void saveMaterial(Material m) {
        materialsList.add(m);
    }

    /**
     * Cerca un material pel seu identificador únic.
     * @param id L'identificador del material.
     * @return L'objecte Material si es troba, o null si no existeix.
     */
    public Material findMaterialById(int id) {
        for (Material m : materialsList) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    
    /**
     * Filtra i retorna una llista de materials segons el seu estat.
     * @param state L'estat pel qual es vol filtrar (ex: DISPONIBLE).
     * @return Llista de materials que coincideixen amb l'estat.
     */
    public List<Material> findMaterialsByState(MaterialStates state) {
        List<Material> result = new ArrayList<>();
        for (Material m : materialsList) {
            if (m.getState() == state) {
                result.add(m);
            }
        }
        return result;
    }

    /**
     * Elimina un material del repositori basant-se en el seu ID.
     * @param id L'identificador del material a eliminar.
     */
    public void deleteMaterial(int id) {
        materialsList.removeIf(m -> m.getId() == id);
    }

    /**
     * Obté tots els materials registrats.
     * @return La llista completa de materials.
     */
    public List<Material> getAllMaterials() {
        return materialsList;
    }

    /**
     * Guarda un registre de préstec a l'historial.
     * @param p L'objecte Prestec a guardar.
     */
    public void savePrestec(Prestec p) {
        prestecsHistory.add(p);
    }

    /**
     * Obté tot l'historial de préstecs.
     * @return La llista completa de préstecs.
     */
    public List<Prestec> getAllPrestecs() {
        return prestecsHistory;
    }

    /**
     * Cerca tots els préstecs realitzats per un usuari concret.
     * @param user El nom o identificador de l'usuari.
     * @return Llista de préstecs associats a l'usuari.
     */
    public List<Prestec> findPrestecsByUser(String user) {
        List<Prestec> userPrestecs = new ArrayList<>();
        for (Prestec p : prestecsHistory) {
            if (p.user.equals(user)) {
                userPrestecs.add(p);
            }
        }
        return userPrestecs;
    }
}
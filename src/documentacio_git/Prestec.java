package documentacio_git;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa una transacció de préstec d'un material a un usuari.
 * Gestiona les dates d'inici, prevista i de retorn, així com el càlcul de retards.
 */
public class Prestec {
    
    /** Identificador únic del préstec. */
    public int idPrestec;
    
    /** L'objecte Material associat a aquest préstec. */
    public Material material;
    
    /** Nom o identificador de l'usuari que té el material. */
    public String user;
    
    /** Data en què es va realitzar el préstec (avui). */
    public LocalDate initialDate;
    
    /** Data límit prevista per a la devolució del material. */
    public LocalDate expectedDate;
    
    /** Data real en què es va retornar el material (null si encara està actiu). */
    public LocalDate endDate;
    
    /** Indica si el préstec està en curs (true) o finalitzat (false). */
    public boolean active;
    
    /**
     * Inicialitza un nou préstec actiu.
     * La data d'inici és avui i la data prevista és d'aquí a 30 dies.
     * * @param material L'objecte Material que es presta.
     * @param user L'identificador o nom de l'usuari que rep el préstec.
     */
    public Prestec(Material material, String user) {
        this.material = material;
        this.user = user;
        this.active = true;
        this.initialDate = LocalDate.now();
        this.expectedDate = initialDate.plusDays(30);
    }
    
    /**
     * Finalitza el préstec.
     * Marca el préstec com a inactiu i registra la data actual com a data de devolució.
     */
    public void endPrestec() {
        this.endDate = LocalDate.now();
        this.active = false;
    }
    
    /**
     * Calcula els dies de retard en la devolució.
     * Si el préstec és actiu, compara amb la data actual.
     * Si el préstec ha finalitzat, compara amb la data de devolució real.
     * * @return El nombre de dies de retard (0 si no hi ha retard o si és negatiu).
     */
    public long retardDays() {
        LocalDate fechaComparacion = (active) ? LocalDate.now() : endDate;
        
        long dias = ChronoUnit.DAYS.between(expectedDate, fechaComparacion);
        return (dias > 0) ? dias : 0;
    }
}
package documentacio_git;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa una transacció de préstec d'un material a un usuari.
 * Gestiona les dates d'inici, prevista i de retorn, així com el càlcul de retards.
 */
public class Prestec {
	public int idPrestec;
	public Material material;
	public String user;
	public LocalDate initialDate;
	public LocalDate expectedDate;
	public LocalDate endDate;
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
	 * * @return El nombre de dies de retard (0 si no hi ha retard).
	 */
	public long retardDays() {
		LocalDate comparedDate = (this.active) ? LocalDate.now() : this.endDate;
		
		long days = ChronoUnit.DAYS.between(this.expectedDate, comparedDate);
		
		return (days > 0) ? days : 0;
	}
}
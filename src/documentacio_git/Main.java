package documentacio_git;

import java.util.List;

/**
 * Classe principal de l'aplicació.
 * <p>
 * Conté el mètode main i serveix per executar una simulació del funcionament
 * del sistema de gestió d'inventari i comprovar que les classes funcionen correctament.
 * </p>
 */
public class Main {

	/**
	 * Mètode d'entrada principal.
	 * Executa una sèrie de proves: afegir materials, prestar-los i retornar-los.
	 * * @param args Arguments de la línia de comandes (no utilitzats).
	 */
	public static void main(String[] args) {
		InventariService service = new InventariService();

		System.out.println("\n--- 1. Añadiendo Materiales ---");
		service.addNewMaterial(1, "Portátil HP", 1001);
		service.addNewMaterial(2, "Proyector Epson", 2002);
		service.addNewMaterial(3, "Tablet Samsung", 3003);

		System.out.println("Total de items en inventario: " + service.countStockTotal());

		System.out.println("\n--- 2. Consultando materiales disponibles (Inicial) ---");
		printDisponibles(service);

		System.out.println("\n--- 3. Realizar un préstamo ---");
		boolean prestamoExitoso = service.registerPrestec("Profesora Paula Molina", 1);

		if (prestamoExitoso) {
			System.out.println("Préstamo realizado con éxito a la profesora Paula Molina.");
		} else {
			System.out.println("Error al realizar el préstamo.");
		}

		System.out.println("\n--- 4. Consultando materiales disponibles (Despues del prestamo) ---");
		printDisponibles(service);

		System.out.println("\n--- 5. Intentando prestar material ya ocupado ---");
		boolean intentoFallido = service.registerPrestec("Alumno Derek Murillo", 1);
		if (!intentoFallido) {
			System.out.println("El sistema no puede prestar el mismo material 2 veces.");
		} else {
			System.err.println("Error: El sistema ha permitido prestar algo que no estaba disponible.");
		}

		System.out.println("\n--- 6. Devolver material ---");
		service.returnMaterial(1);
		
		printDisponibles(service);

	}

	/**
	 * Mètode auxiliar per imprimir per consola la llista de materials disponibles.
	 * * @param service La instància del servei d'inventari per consultar dades.
	 */
	private static void printDisponibles(InventariService service) {
		List<Material> disponibles = service.getAvailableMaterials();
		
		if (disponibles.isEmpty()) {
			System.err.println("No hay materiales disponibles");
		} else {
			for (Material m : disponibles) {
				System.out.println("- [" + m.getId() + "] " + m.getName() + " | Estado: " + m.getState());
			}
		}
	}
}
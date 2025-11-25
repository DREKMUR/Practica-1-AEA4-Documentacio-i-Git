package documentacio_git;

import java.util.List;

public class Main {

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
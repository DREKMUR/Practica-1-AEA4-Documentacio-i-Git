package documentacio_git;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal de l'aplicació.
 * <p>
 * Conté el menú principal per interactuar amb el sistema de gestió d'inventari.
 * Permet afegir materials, prestar-los, retornar-los i llistar-los.
 * </p>
 * @author Derek Murillo Fernandez
 */
public class Main {
    /**
     * Mètode d'entrada principal.
     * Inicia l'aplicació i mostra el menú d'opcions.
     * @param args Arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        InventariService service = new InventariService();
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;

        // Bucle del menú principal
        while (!sortir) {
            System.out.println("\n=== MENÚ DE GESTIÓ D'INVENTARI ===");
            System.out.println("1. Afegir nou material");
            System.out.println("2. Realitzar préstec");
            System.out.println("3. Retornar material");
            System.out.println("4. Veure materials disponibles");
            System.out.println("5. Veure stock total");
            System.out.println("0. Sortir");
            System.out.print("Selecciona una opció: ");

            // Llegim l'opció com a text i la passem a numero per evitar errors de salt de línia
            int opcio = Integer.parseInt(scanner.nextLine());

            switch (opcio) {
                case 1:
                    // Opció 1: Afegir Material
                    System.out.println("\n--- Afegir Material ---");
                    System.out.print("Introdueix l'ID del material (numèric): ");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Introdueix el nom del material: ");
                    String nom = scanner.nextLine();
                    
                    System.out.print("Introdueix el número de sèrie: ");
                    int serie = Integer.parseInt(scanner.nextLine());
                    
                    service.addNewMaterial(id, nom, serie);
                    System.out.println("Material afegit correctament!");
                    break;

                case 2:
                    // Opció 2: Prestar
                    System.out.println("\n--- Realitzar Préstec ---");
                    System.out.print("Nom de la persona que agafa el material: ");
                    String usuari = scanner.nextLine();
                    
                    System.out.print("ID del material a prestar: ");
                    int idPrestar = Integer.parseInt(scanner.nextLine());
                    
                    boolean prestecFet = service.registerPrestec(usuari, idPrestar);
                    
                    if (prestecFet) {
                        System.out.println("Préstec realitzat amb èxit a " + usuari + ".");
                    } else {
                        System.out.println("Error: El material no existeix o ja està prestat.");
                    }
                    break;

                case 3:
                    // Opció 3: Retornar
                    System.out.println("\n--- Retornar Material ---");
                    System.out.print("ID del material a retornar: ");
                    int idRetorn = Integer.parseInt(scanner.nextLine());
                    
                    service.returnMaterial(idRetorn);
                    System.out.println("Operació de retorn processada.");
                    break;

                case 4:
                    // Opció 4: Llistar disponibles
                    System.out.println("\n--- Materials Disponibles ---");
                    printDisponibles(service);
                    break;

                case 5:
                    // Opció 5: Stock Total
                    System.out.println("\n--- Stock Total ---");
                    int total = service.countStockTotal();
                    System.out.println("Total d'ítems registrats a l'inventari: " + total);
                    break;

                case 0:
                    // Opció 0: Sortir
                    System.out.println("Tancant l'aplicació...");
                    sortir = true;
                    break;

                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
            }
        }
        
        scanner.close();
    }

    /**
     * Mètode auxiliar per imprimir per consola la llista de materials disponibles.
     * @param service La instància del servei d'inventari per consultar dades.
     */
    private static void printDisponibles(InventariService service) {
        List<Material> disponibles = service.getAvailableMaterials();
        
        if (disponibles.isEmpty()) {
            System.out.println("No hi ha materials disponibles en aquest moment.");
        } else {
            for (Material m : disponibles) {
                System.out.println("- [ID: " + m.getId() + "] " + m.getName() + " | Estat: " + m.getState());
            }
        }
    }
}
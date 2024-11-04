package Inventory;

import Inventory.Managers.*;
import java.util.Scanner;

public class InventoryManager {
  private MedicineManager medicineManager;
  private TestManager testManager;
  private EquipmentManager equipmentManager;

  public InventoryManager() {
    this.medicineManager = new MedicineManager();
    this.testManager = new TestManager();
    this.equipmentManager = new EquipmentManager();
  }

  public void inventoryOperations() {
    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    do {
      System.out.println();
      System.out.println("\u001B[96m1. Medicine Management System\u001B[0m");
      System.out.println("\u001B[96m2. Tests Management System\u001B[0m");
      System.out.println("\u001B[96m3. Equipment Management System\u001B[0m");
      System.out.println("\u001B[96m4. Exit\u001B[0m");

      System.out.println("\u001B[93mEnter choice: \u001B[0m");
      if (scanner.hasNextInt())
        choice = scanner.nextInt();
      if (scanner.hasNextLine())
        scanner.nextLine();

      switch (choice) {
        case 1:
          medicineManager.medicineOperations();
          break;
        case 2:
          testManager.testOperations();
          break;
        case 3:
          equipmentManager.equipmentOperations();
          break;
        case 4:
          System.out.println("\u001B[91mExiting Inventory Management System.\u001B[0m");
          break;

        default:
          System.out.println("\u001B[91mInvalid choice\u001B[0m");
          break;
      }

    } while (choice != 4);
  }

}

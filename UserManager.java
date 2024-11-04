package Users;

import Users.Managers.*;
import java.util.Scanner;

public class UserManager {

  private DoctorManager doctorManager;
  private PatientManager patientManager;

  public UserManager() {
    this.doctorManager = new DoctorManager();
    this.patientManager = new PatientManager();
  }

  public void userManagementSystem() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\u001B[95mUser Management System");
      System.out.println("1. \u001B[95mDoctor Management System");
      System.out.println("2. \u001B[95mPatient Management System");
      System.out.println("3. \u001B[0mExit\u001B[0m");
      System.out.print("\u001B[92mEnter your choice: \u001B[0m");

      try {
        choice = scanner.nextInt();

        switch (choice) {
          case 1:
            doctorManager.doctorOperations();
            break;
          case 2:
            patientManager.patientOperations();
            break;
          case 3:
            System.out.println("\u001B[92mExiting User Management System.\u001B[0m");
            break;
          default:
            System.out.println("\u001B[91mInvalid choice. Please try again.\u001B[0m");
        }
      } catch (NumberFormatException e) {
        System.out.println("\u001B[91mInvalid input. Please enter a valid integer.\u001B[0m");
        choice = 0;
      }

    } while (choice != 3);
  }
}

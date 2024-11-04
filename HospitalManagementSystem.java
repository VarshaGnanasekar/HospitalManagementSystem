import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Main.Components.PatientOperations;
import Main.Components.DoctorOperations;
import Main.Components.Admin;
import Users.Managers.*;
import Documents.Components.*;

public class HospitalManagementSystem {

	private HashMap<String, Credential> userCredentials;

	HospitalManagementSystem() {
		this.userCredentials = new HashMap<>();
		userCredentials.put("Admin",new  Credential("Adm@7","Admin"));
		loadUserCredentials("Users/Files/doctors.csv", "Doctor");
		loadUserCredentials("Users/Files/patients.csv", "Patient");
	}

	private void loadUserCredentials(String filePath, String role) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine())!= null) {
				String[] parts = line.split(",");

				if (parts.length >= 2) {
					String username = parts[2];
					String password = parts[3];

					userCredentials.put(username, new Credential(password, role));
				} else {
					System.out.println("Skipping line: " + line + " - Insufficient elements");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean isStrongPassword(String password)
  {
			String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	  Pattern pattern = Pattern.compile(regex);
	  Matcher matcher = pattern.matcher(password);
	  return matcher.matches();
  }


  private void displayUserCredentials() {
      System.out.println("\u001B[36mUser Credentials:");
      for (String username : userCredentials.keySet()) {
          Credential credential = userCredentials.get(username);
          System.out.println("Username: " + username + ", Password: " + credential.getPassword() + ", Role: " + credential.getRole());
      }
      System.out.println("\u001B[0m");
  }


	private void login(Scanner scanner) {
		System.out.print("\u001B[94mEnter your username:\u001B[0m");
		String username = scanner.nextLine();
		int count = 1;

		if (!userCredentials.containsKey(username)) {
			System.out.println("\u001B[91Username not found. Please enter a valid username.\u001B[0m");
			return;
		} else {
			do {
				System.out.print("\u001B[94mEnter your password:\u001B[0m");
				String password = scanner.nextLine();
				Credential credential = userCredentials.get(username);

				if (password.equals(credential.getPassword())) {
          System.out.println("\u001B[92mLogin successful. Welcome, " + username + "!\u001B[0m");

					try {
						if (credential.getRole().equals("Doctor")) {
							DoctorOperations d = new DoctorOperations(username);
							d.doctorOperations();
							break;
						} else if (credential.getRole().equals("Patient")) {
							PatientOperations p = new PatientOperations(username);
							p.patientOperations();
							break;

						}else if (credential.getRole().equals("Admin")){
							Admin a = new Admin();
							a.adminOperations();
							break;
						}

					} catch (Exception e) {
            System.out.println("\u001B[91mError during login: " + e.getMessage() + "\u001B[0m");
            e.printStackTrace();

					}
				} else {
					count++;
					if (count < 5) {
						System.out.println("\u001B[91Incorrect password. Please try again.\u001B[0m");
					} else {
						System.out.println("\u001B[91Invalid login Credentials!");
					}
				}
			} while (count != 6);
	}
		}


	private void register(Scanner sc)
	{

		System.out.print("\u001B[94mEnter your name:\u001B[0m");
		String name=sc.nextLine();
		do
		{
		System.out.print("\u001B[94mEnter your password:\u001B[0m");
		String password=sc.nextLine();

		if (isStrongPassword(password))
		{
        System.out.println("\u001B[92mPassword is strong.\u001B[0m");

			  System.out.print("ENTER YOUR ROLE(DOCTOR OR PATIENT):");
		String role=sc.nextLine();
		if(role.equalsIgnoreCase("doctor"))
		{
		DoctorManager doctorManager = new DoctorManager();
		doctorManager.addDoctor(name,password);
		break;
		}
		else if(role.equalsIgnoreCase("Patient"))
		{
		PatientManager patientManager = new PatientManager();
				patientManager.addPatient(name,password);	
		break;
		}
		}
		else 
		{
				  System.out.println("\u001B[91mPassword is not strong. Please follow the criteria and reenter.\u001B[0m");
				}
		}while(true);
	}

	private static class Credential {
		private String password;
		private String role;

		public Credential(String password, String role) {
			this.password = password;
			this.role = role;
		}

		public String getPassword() {
			return password;
		}

		public String getRole() {
			return role;
		}
	}

	public static void main(String[] args) {
		HospitalManagementSystem hospitalManagementSystem = new HospitalManagementSystem();
		Scanner sc= new Scanner(System.in);


    System.out.println("\u001B[95m1. Login");
    System.out.println("2. Register\u001B[0m");
    System.out.print("\u001B[33mEnter your choice:\u001B[0m");


		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
			case 1:
				hospitalManagementSystem.login(sc);
				break;

			case 2:
		hospitalManagementSystem.register(sc);
		break;

	  }
	}
}

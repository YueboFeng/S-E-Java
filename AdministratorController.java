import java.util.Scanner;

public class AdministratorController {
    public void controlAdministration()
    {
        AdministratorBoundary administration = new AdministratorBoundary();
        Scanner scanner = new Scanner(System.in);
        boolean result = true;
        while (result) {
        System.out.println("\nPress 1 to manage Schedule\nPress 2 to manage Capacity\nPress 3 to add Credit\nPress 4 to cancel class\nPress other keys to Logout");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                administration.manageScheduleByAdmin();
                break;
            case "2":
                administration.manageCapacityByAdmin();
                break;
            case "3":
                administration.addCreditByAdmin();
                break;
            case "4":
                administration.cancelClass();
                break;
            default:
                System.out.println("\nLogging out...");
                result = false;
                break;
            }
        }
    }
}

public class MenuController {

    MWBS mwbs = new MWBS();

    public void isMemberOrAdmin(String email)
    {
        MenuController menu = new MenuController();
        if (email != null)
        {
            menu.displayMemberMenu(email);
        }
        else
        {
            menu.displayAdminMenu();
        } 
    }

    public void displayMemberMenu(String email)
    {
        MemberController memberController = new MemberController();
        System.out.println("\n========== Member System ==========");
        memberController.controlMemberBehaviour(email);
    }

    public void displayAdminMenu() {
        AdministratorController administratorController = new AdministratorController();
        System.out.println("\n========== Admin System ==========");
        administratorController.controlAdministration();
    }
}

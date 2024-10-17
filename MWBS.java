public class MWBS
{
    public MWBS(){

    }

    public static void startProgram(){
        LoginController loginController = new LoginController();
        MenuController menu = new MenuController();
        while (true)
        {
            String email = loginController.loginFunction();
            menu.isMemberOrAdmin(email);
        }
    }
    
    public static void main(String[] args)
    {    
        startProgram();

    }
}
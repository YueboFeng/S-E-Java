import java.util.*;

public class LoginController {
    public LoginController(){

    }
    
    public String loginFunction(){

        boolean loginOutcome = false;
        Scanner scan = new Scanner(System.in);

        LoginBoundary loginBoundary = new LoginBoundary();
        String loginChoice = loginBoundary.loginInput();
        String email = "";
        String password = "";
        while(!loginOutcome){
            System.out.print("Please enter your email: ");
            email = scan.nextLine();
            System.out.print("Please enter your password: ");
            password = scan.nextLine();
            loginOutcome = loginBoundary.validation(loginChoice, email, password); 
        }
        if (loginChoice.equals("1"))
        {
            return email;
        }
        else
        {
            return null;
        }  
    }
}
import java.util.*;

public class LoginBoundary {

    public LoginBoundary(){

    }

    public String loginInput(){
        System.out.println("Welcome to use MWBS!");
        String choice = "";
        while(true){
            System.out.println("Please choose your login type:\n-Press 1 for member login\n-Press 2 for administrator login:\n-Press ctrl + c to quit");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("You've chosen member login.");
                    return choice;
                case "2":
                    System.out.println("You've chosen administrator login.");
                    return choice;
                default:
                    System.out.println("You can only input 1 or 2");
            }
        }
    }

    public boolean validation(String loginType, String email, String password){
        FileIO fileIO = new FileIO();
        if (loginType.equals("1")){
            ArrayList<Member> memberList = fileIO.getAllMember();
            for(Member currentMember: memberList){
                if(email.equals(currentMember.getEmail())){
                    if (password.equals(currentMember.getPassword())){
                        System.out.println("Member login success!");
                        return true;
                    }
                    else{
                        System.out.println("Incorrect password!");
                        return false;
                    }
                }
            }
            System.out.println("Invalid email!");
        }else{
            String[] adminList = fileIO.readAdmin().split("\n");
            for(int i=0;i<adminList.length;i++){
                String[] admin = adminList[i].split(",");
                if(email.equals(admin[1])){
                    if (password.equals(admin[2])){
                        System.out.println("Admin login success!");
                        return true;
                    }
                    else{
                        System.out.println("Incorrect password!");
                        return false;
                    }
                }
            }
            System.out.println("Invalid email!");
        }
        
        return false;
    }   
}

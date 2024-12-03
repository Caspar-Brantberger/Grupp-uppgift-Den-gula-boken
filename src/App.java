import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    Contact cs = new Contact();
    Login ln = new Login();
    Input in = new Input();
    public static Scanner scanner = new Scanner(System.in);

    public static boolean runProgram = true;

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;

    public void app() {

                //createContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
                //createContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
                //createContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");
                appLoop();



            }

    public void appLoop(){
                while(runProgram) {

                    while(!isLoggedIn) {

                        ln.loginPrompt();
                    }

                    if(isAdmin && runProgram) {

                        in.adminInput();
                    }
                    else if(runProgram) {

                        in.guestInput();
                    }
                }
            }

            public static String isNumber(String user){

                while (!user.matches("(\\d*)")){
                    System.out.println(" ");
                    System.out.println("Invalid input, please insert a number");
                    user = scanner.nextLine();
                }

                return user;
            }


        }







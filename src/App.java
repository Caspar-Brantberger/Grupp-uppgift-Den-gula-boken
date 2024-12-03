public class App
{
    static Contact[] contacts = new Contact[0];
    static LoginFunctions loginFunctions = new LoginFunctions();
    static CreateFunctions createFunctions = new CreateFunctions();

    public void run(){

        createFunctions.createContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
        createFunctions.createContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
        createFunctions.createContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");

        while(loginFunctions.runProgram) {

            while(!loginFunctions.isLoggedIn) {
                loginFunctions.loginPrompt();
            }

            if(loginFunctions.isAdmin && loginFunctions.runProgram) {
                loginFunctions.adminInput();
            }
            else if(loginFunctions.runProgram) {
                loginFunctions.guestInput();
            }
        }
    }

    public void displayContacts(){

        System.out.println(" ");

        for (Contact c : contacts){
            c.displayContact();
        }
    }
}
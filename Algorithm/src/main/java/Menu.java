import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static Map<String, String> colors  = new HashMap<String, String>() {{
        put("black", ANSI_BLACK);
        put("blue", ANSI_BLUE);
        put("yellow", ANSI_YELLOW);
        put("red", ANSI_RED);
    }};

    public static void printSeparator(int length){
        for (int i=0; i<length+2 ;i++){
            System.out.print("-------");
        }
        System.out.println();
    }


    public static void printHeader(){
        System.out.println(ANSI_YELLOW + "                  ____    __    ____  _______  __        ______   ______   .___  ___.  _______                                    ");
        System.out.println("                  \\   \\  /  \\  /  / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|                                   ");
        System.out.println("                   \\   \\/    \\/  /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__                                      ");
        System.out.println("                    \\           /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|                                     ");
        System.out.println("                     \\   / \\   /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____                                    ");
        System.out.println("                      \\_/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|                                   \n");
        System.out.println("                                             .___________.  ______                                                                ");
        System.out.println("                                             |           | /  __  \\                                                               ");
        System.out.println("                                             `---|  |----`|  |  |  |                                                              ");
        System.out.println("                                                 |  |     |  |  |  |                                                              ");
        System.out.println("                                                 |  |     |  `--'  |                                                              ");
        System.out.println("                                                 |__|      \\______/                                                               ");
        System.out.println(".______       __    __  .___  ___. .___  ___. ____    ____         _______.  ______    __      ____    ____  _______ .______      ");
        System.out.println("|   _  \\     |  |  |  | |   \\/   | |   \\/   | \\   \\  /   /        /       | /  __  \\  |  |     \\   \\  /   / |   ____||   _  \\     ");
        System.out.println("|  |_)  |    |  |  |  | |  \\  /  | |  \\  /  |  \\   \\/   /        |   (----`|  |  |  | |  |      \\   \\/   /  |  |__   |  |_)  |    ");
        System.out.println("|      /     |  |  |  | |  |\\/|  | |  |\\/|  |   \\_    _/          \\   \\    |  |  |  | |  |       \\      /   |   __|  |      /     ");
        System.out.println("|  |\\  \\----.|  `--'  | |  |  |  | |  |  |  |     |  |        .----)   |   |  `--'  | |  `----.   \\    /    |  |____ |  |\\  \\----.");
        System.out.println("| _| `._____| \\______/  |__|  |__| |__|  |__|     |__|        |_______/     \\______/  |_______|    \\__/     |_______|| _| `._____|");
        System.out.println(ANSI_RED + "\nCreated by Victor Xirau" + ANSI_RESET);
    }

    public static void printDeckHeader(){
        System.out.println("\n\n __  __                           __             __            ");
        System.out.println(" \\ \\/ / ___  __ __  ____      ___/ / ___  ____  / /__       (_)");
        System.out.println("  \\  / / _ \\/ // / / __/     / _  / / -_)/ __/ /  '_/          ");
        System.out.println("  /_/  \\___/\\_,_/ /_/        \\_,_/  \\__/ \\__/ /_/\\_\\      (_)  \n");
    }

    public static void printBoardHeader(){
        System.out.println("\n\n   ___                       __        _ ");
        System.out.println("  / _ ) ___  ___ _  ____ ___/ /       (_)");
        System.out.println(" / _  |/ _ \\/ _ `/ / __// _  /       _   ");
        System.out.println("/____/ \\___/\\_,_/ /_/   \\_,_/       (_)  \n");
    }

    public static void printError(String message){
        System.out.println(ANSI_RED + "\n" + message + "\n" + ANSI_RESET);
    }

    public static void printSuccess(String message){
        System.out.print(ANSI_GREEN + "\n" + message + ANSI_RESET);
    }

    public static void printGoodBye(){
        System.out.println(ANSI_GREEN + "\n\n-----------------------------------------------");
        System.out.println("Thankyou for using Rummy Solver by Victor Xirau");
        System.out.println("-----------------------------------------------\n" + ANSI_RESET);
    }

    public static void printMenu(){
        System.out.println(ANSI_PURPLE + "\n\nPlease select the option by inputing the number" + ANSI_RESET);
        System.out.println("\t1. Restart Game");
        System.out.println("\t2. View my Deck");
        System.out.println("\t3. View Board");
        System.out.println("\t4. Get Next Move");
        System.out.println("\t5. Exit");
        System.out.print(ANSI_PURPLE + "Selected Option: " + ANSI_RESET);
    }

    public static void switchPeca(Piece p, int i){
        switch(i){
            case 0:
                System.out.print(".___.   ");
                break;
            case 1:
                String num = " " + p.getNumber();
                if(p.getNumber().toString().length()==1){
                    num+=" ";
                }
                if(p.getNumber() == 14){
                    num = "ʘ‿ʘ";
                }
                System.out.print("|" + colors.get(p.getColor()) + num + ANSI_RESET + "|   ");
                break;
            case 2:
                System.out.print("|___|   ");
                break;
        }
    }

    public static void printHorizontalDeck(ArrayList<Piece> pieces){
        for(int i=0; i<3 ;i++){
            for(Piece p : pieces) {
                switchPeca(p, i);
            }
            System.out.println();
        }
    }

    public static void printHorizontalDeck(Piece pieces[]){

            for (int i = 0; i < 3; i++) {
                for (Piece p : pieces) {
                    if(p!=null) {
                        switchPeca(p, i);
                    }
                }
                System.out.println();
            }
    }

}

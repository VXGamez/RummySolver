import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Principal {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        try {
            boolean exit = false;
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/main/resources/inputFile.json"));
            Game data = gson.fromJson(reader, Game.class);

            System.out.println(ANSI_YELLOW + "\n\n--------------------------------------" + ANSI_RESET);
            System.out.println("ＷＥＬＣＯＭＥ ＴＯ ＲＵＭＭＹ ＳＯＬＶＥＲ" + ANSI_RESET);
            System.out.println(ANSI_RED + "Created by Victor Xirau" + ANSI_RESET);
            System.out.print(ANSI_YELLOW + "--------------------------------------" + ANSI_RESET);
            Scanner scan = new Scanner(System.in);
            String regex = "[1-4]";
            while(!exit){
                System.out.println(ANSI_PURPLE + "\n\nPlease select the option by inputing the number" + ANSI_RESET);
                System.out.println("\t1. Generate new Deck");
                System.out.println("\t2. View my deck");
                System.out.println("\t3. Get Next Move");
                System.out.println("\t4. Exit");
                System.out.print(ANSI_PURPLE + "Selected Option: " + ANSI_RESET);
                String input = scan.nextLine();
                if(input.length()!=1 ||!Pattern.matches(regex, input)){
                    System.out.println(ANSI_RED + "\nSomething was wrong with your input. Please choose again.\n" + ANSI_RESET);
                }else{
                    int opcio = Integer.parseInt(input);
                    switch (opcio){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            System.out.println(ANSI_GREEN + "\n\n-----------------------------------------------");
                            System.out.println("Thankyou for using Rummy Solver by Victor Xirau");
                            System.out.println("-----------------------------------------------\n" + ANSI_RESET);
                            exit = true;
                            break;
                    }
               }
            }


        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "\n\nInput Files Missing\n\n" + ANSI_RESET);
        }
    }
}

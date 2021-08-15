import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Principal {

    public static void main(String[] args) {
        try {
            boolean exit = false;
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/main/resources/inputFile.json"));
            Game data = gson.fromJson(reader, Game.class);
            Menu.printHeader();
            Scanner scan = new Scanner(System.in);
            String regex = "[1-5]";
            while(!exit){
                Menu.printMenu();
                String input = scan.nextLine();
                if(input.length()!=1 ||!Pattern.matches(regex, input)){
                    Menu.printError("Something was wrong with your input. Please choose again.");
                }else{
                    int opcio = Integer.parseInt(input);
                    switch (opcio){
                        case 1:
                            //RESTART GAME
                            exit = Functions.readJson();
                            data = Functions.generateDeck(data);
                            Menu.printSuccess("Deck generated!");
                            break;
                        case 2:
                            //VIEW MY DECK
                            if(Functions.allAvailable == null){
                                Menu.printError("Please create a deck first");
                            }else {
                                Menu.printDeckHeader();
                                Menu.printSeparator(data.getMyPieces().size());
                                Menu.printHorizontalDeck(data.getMyPieces());
                                Menu.printSeparator(data.getMyPieces().size());
                            }
                            break;
                        case 3:
                            //VIEW BOARD
                            Menu.printBoardHeader();
                            int biggest = Functions.findBiggestGroup(data.getBoard());
                            Menu.printSeparator(biggest);
                            for(PieceGroup pg : data.getBoard()){
                                Menu.printHorizontalDeck(pg.getPieces());
                                System.out.println();
                            }
                            Menu.printSeparator(biggest);
                            break;
                        case 4:
                            //GET NEXT MOVE

                            break;
                        case 5:
                            //EXIT
                            Menu.printGoodBye();
                            exit = true;
                            break;
                    }
               }
            }


        } catch (FileNotFoundException e) {
            Menu.printError("Input files missing.");
        }
    }
}

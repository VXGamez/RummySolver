import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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

                            ArrayList<PieceGroup> solucions = Functions.getAllDeckCombos(data.getMyPieces());
                            /*biggest = Functions.findBiggestGroup(solucions);
                            Menu.printSeparator(biggest);
                            for(PieceGroup pg : solucions){
                                Menu.printHorizontalDeck(pg.getPieces());
                                System.out.println();
                            }
                            Menu.printSeparator(biggest);*/


                            /*
                            Ordre de funcionalitats:

                            FASE1

                      DONE  1- Detectar les que es poden fer amb el propi deck
                            2- Controlar la norma inicial de que ha de sumar >=30
                            3- Si no es pot fer res, agafar una fitxa més

                            FASE2 (Primera solució)

                            1- Buscar grups on poden afegir fitxes individuals del meu deck, al tauler
                            2- Separar grups existents a la taula
                                Ex: Si hi ha un grup on es compleixi => min<Ficha<max && color == ficha.color mirar de separar-lo en dos sempre i quants siguin 2 de 3, i afegir la fitxa
                            3- Mostrar pas a pas el que s'ha fet

                            FASE3 (Solució més òptima)

                            1- FASE2 però enlloc de trobar la primera solució, es provaran totes les possibles solucions i es triarà la que maximitzi el nombre de fitxes alliberades del dock

                            FASE4

                            1- Combinar grups de la taula entre si, si això afavoreix el colocar una peça a la taula


                            */

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

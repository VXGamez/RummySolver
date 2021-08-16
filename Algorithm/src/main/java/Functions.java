import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.util.concurrent.ThreadLocalRandom;


import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Functions {

    public static ArrayList<Piece> allAvailable;

    public static boolean hasQuarentine = true;


    public static boolean readJson(){
        try {
            Gson gson = new Gson();
            Type pieceList = new TypeToken<ArrayList<Piece>>(){}.getType();
            JsonReader reader = new JsonReader(new FileReader("src/main/resources/tiles.json"));
            allAvailable = gson.fromJson(reader, pieceList);
            return false;
        }catch (Exception e) {
            Menu.printError("Input Files Missing.");
            return true;
        }
    }

    public static Game generateDeck(Game current){
        ArrayList<Piece> newPieces = new ArrayList<>();
        for(int i=0; i<14 ;i++){
            newPieces.add(getNextPiece());
        }
        current.setMyPieces(newPieces);
        return current;
    }



    public static int findBiggestGroup(ArrayList<PieceGroup> pg){
        int biggest = 0;
        for(PieceGroup pp : pg){
            if(pp.getPieces().size() > biggest){
                biggest = pp.getPieces().size();
            }
        }
        return biggest;
    }

    public static Piece getNextPiece(){
        int index = ThreadLocalRandom.current().nextInt(0, allAvailable.size());
        Piece p = allAvailable.get(index);
        allAvailable.remove(index);
        return p;
    }

    public static boolean areSameColor(ArrayList<Piece> p){
        String color = p.get(0).getColor();
        for(Piece k : p){
           if(!k.getColor().equals(color)){
               return false;
           }
        }
        return true;
    }

    public static boolean xorColors(ArrayList<Piece> p){
        ArrayList<String> colors = new ArrayList<>();
        for(Piece k : p){
            if(colors.contains(k.getColor())){
                return false;
            }else{
                colors.add(k.getColor());
            }
        }
        return true;
    }

    public static boolean areStraight(ArrayList<Piece> p){
        for(int i=0; i<p.size()-1 ;i++){
            if(p.get(i).getNumber() != p.get(i+1).getNumber()-1){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidGrup(PieceGroup pg){

        if(pg.isSame() && xorColors(pg.getPieces()) && pg.getPieces().size()>=3){
            return true;
        }

        if(!pg.isSame() && areSameColor(pg.getPieces()) && pg.getPieces().size()>=3 && areStraight(pg.getPieces())){
            return true;
        }

        return false;
    }

    public static ArrayList<PieceGroup> trobats;

    public static ArrayList<PieceGroup> getAllDeckCombos(ArrayList<Piece> peces){
        trobats = new ArrayList<>();
        getPieceGroups(1, peces, true);
        trobats.removeIf(k -> !isValidGrup(k));
        return trobats;
    }

    public static ArrayList<Piece> hasNumber(ArrayList<Piece> peces, int number){
        ArrayList<Piece> pzs = new ArrayList<>();
        for(Piece p : peces){
            if(p.getNumber() == number){
                pzs.add(p);
            }
        }
        return pzs;
    }

    //trobats

    public static void getPieceGroups(int valor, ArrayList<Piece> peces, boolean isFirst){
        ArrayList<Piece> numbers = hasNumber(peces, valor);
        for(Piece p : numbers){
            if(!isFirst){
                for(PieceGroup pg : trobats){

                }
            }

        }
    }

    /*
    ---------------------- PSEUDO ----------------------
    //runs == vector de tamany k initialized for each suit to a multi-set of size m with precisely m zeros.
    maxScore(1, runs);

    func maxScore(value, runs){
        //n == 13
        if(value>n){
            return 0;
        }
        //Score == Vector de n × k × f(m) that contains the maximum score that can be obtained given this state of the puzzle.
        if(score[value][runs] > -inf ){
            return score[value][runs]
        }

        for(runs', runscores in makeRuns(runs)){
            groupScores = totalGroupSize(hand/runs')*value;
            result = groupScores + runScores + maxScore(value+1, runs');
            score[value][runs] = max(result, score[value][runs]);
        }

        return score[value][runs]
    }

    */


}

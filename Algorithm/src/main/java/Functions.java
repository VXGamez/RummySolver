import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

    public static boolean areSameColor(Piece[] p){
        String color = p[0].getColor();
        for(Piece k : p){
            if(k!=null){
                if(!k.getColor().equals(color)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean xorColors(Piece[] p){
        ArrayList<String> colors = new ArrayList<>();
        for(Piece k : p){
            if(k!=null){
                if(colors.contains(k.getColor())){
                    return false;
                }else{
                    colors.add(k.getColor());
                }
            }
        }
        return true;
    }

    public static Comparator<Piece> myComparator = new Comparator<Piece>() {
        @Override
        public int compare(Piece o1, Piece o2) {
            return o1.getNumber().compareTo(o2.getNumber());
        }
    };

    public static boolean areStraight(Piece[] p){
        ArrayList<Piece> done = new ArrayList<>();
        for(int i=0; i<p.length-1 ;i++){
            if(p[i]!=null && p[i+1]!=null){
                if(Math.abs(p[i].getNumber()-p[i+1].getNumber()) != 1 || done.contains(p[i]) ){
                    done.clear();
                    return false;
                }else{
                    done.add(p[i]);
                }
            }
        }
        done.clear();
        return true;
    }

    public static boolean checkIfSame(Piece[] pg){

        int num = pg[0].getNumber();
        for(Piece k : pg){
            if(k!=null && k.getNumber() != num){
                return false;
            }
        }
        return true;
    }

    public static <T> int getLength(T[] arr){
        int count = 0;
        for(T el : arr)
            if (el != null)
                ++count;
        return count;
    }

    public static boolean isValidGrup(Piece[] pg, boolean isSearching){

        if(pg[0]==null){
            return false;
        }
        if(checkIfSame(pg) && xorColors(pg)){
            if(!isSearching && getLength(pg)>=3){
                return true;
            }else if(isSearching){
                return true;
            }
        }

        if(!checkIfSame(pg) && areSameColor(pg) && areStraight(pg)){
            if(!isSearching && getLength(pg)>=3){
                return true;
            }else return isSearching;
        }

        return false;
    }


    public static ArrayList<PieceGroup> getAllDeckCombos(ArrayList<Piece> peces){
        allCombos = new ArrayList<>();
        Piece data[] = new Piece[peces.size()];
        Piece clone[] = new Piece[peces.size()];
        //peces.sort(myComparator);

        System.out.println("Deck Before");
        Menu.printSeparator(peces.size());
        Menu.printHorizontalDeck(peces);
        Menu.printSeparator(peces.size());

        getPieceGroups(peces, data, clone, 0, 0, peces.size());
        checkQuarentine();
        if(hasQuarentine){
            Menu.printError("Deck is not valid. Pieces have to sum >=30");
        }
        System.out.println("Deck After");
        Menu.printSeparator(peces.size());
        Menu.printHorizontalDeck(peces);
        Menu.printSeparator(peces.size());
        return null;
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

    public static void checkQuarentine(){
        int total=0;
        for(ArrayList<Piece> arr : allCombos){
            for(Piece p : arr){
                total += p.getNumber();
            }
        }
        if( total >= 30){
            hasQuarentine = false;
        }
    }

    private static ArrayList<ArrayList<Piece>> allCombos;

    public static boolean notFoundYet(Piece[] arr){
        ArrayList<Piece> p = new ArrayList<Piece>(Arrays.asList(arr));
        p.removeAll(Collections.singletonList(null));
        for(ArrayList<Piece> kk : allCombos){
            if(kk.equals(p)){
                return false;
            }
        }
        return true;
    }

    public static void getPieceGroups(ArrayList<Piece> peces, Piece[] arr, Piece[] clone, int index,int start, int end){

        if(start > peces.size()-1 || index > peces.size()-1){
            if(isValidGrup(arr, false) && notFoundYet(arr)){
                Menu.printSeparator(arr.length);
                Menu.printHorizontalDeck(arr);
                Menu.printSeparator(arr.length);
                peces.removeAll(Arrays.asList(arr));
                ArrayList<Piece> pp = new ArrayList<Piece>(Arrays.asList(arr));
                pp.removeAll(Collections.singletonList(null));
                allCombos.addAll(Collections.singleton(pp));
                arr = new Piece[peces.size()];
                clone = new Piece[peces.size()];
                getPieceGroups(peces, arr, clone,  0, 0, peces.size());
            }
            return;
        }

        for (int k=start; k<end; k++){
            clone[index] = peces.get(k);
            if(isValidGrup(clone, true)){
                arr[index] = peces.get(k);
                ArrayList<Piece> newP = (ArrayList<Piece>)peces.clone();
                newP.removeAll(new ArrayList<>(Arrays.asList(arr)));
                getPieceGroups(newP, arr, clone,  index+1, 0, newP.size());
            }
        }

        //getPieceGroups(peces, arr, clone, index,  i+1);




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

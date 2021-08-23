import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class Functions {

    public static ArrayList<Piece> allAvailable;
    private static ArrayList<Piece> piezas;
    public static boolean hasQuarentine = true;
    private static ArrayList<ArrayList<Piece>> allCombos = new ArrayList<>();
    private static HashMap<Integer, ArrayList<ArrayList<Piece>>> resultats = new HashMap<>();
    private static int id;

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

    public static ArrayList<PieceGroup> getAllDeckCombos(ArrayList<Piece> peces){
        long start = System.currentTimeMillis();
        allCombos = new ArrayList<>();
        resultats = new HashMap<>();
        piezas = peces;
        id = 0;
        ArrayList<Piece> data = new ArrayList<>();
        System.out.println("\n");
        System.out.println("Deck Before");
        Menu.printSeparator(piezas.size());
        Menu.printHorizontalDeck(piezas);
        Menu.printSeparator(piezas.size());

        getPieceGroups(piezas, data, piezas.size(), -1, 0);
        /*checkQuarentine();
        if(hasQuarentine){
            Menu.printError("Deck is not valid. Pieces have to sum >=30");
        }*/
        System.out.println("Deck After");
        Menu.printSeparator(piezas.size());
        Menu.printHorizontalDeck(piezas);
        Menu.printSeparator(piezas.size());

        long time = System.currentTimeMillis() - start;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
        long nanos = TimeUnit.MILLISECONDS.toNanos(time);

        System.out.println();
        Menu.printSuccess("Ha trigat: " + minutes + "min " + seconds + "s " + time + "ms " + nanos + "ns ");
        System.out.println();

        return new ArrayList<>();
    }

    public static int findBiggestGroup(ArrayList<PieceGroup> pg){
        int biggest = 0;
        if(pg == null){
            return 0;
        }
        for(PieceGroup pp : pg){
            if(pp != null){
                if(pp.getPieces().size() > biggest){
                    biggest = pp.getPieces().size();
                }
            }else{
                return biggest;
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
            if(k!=null){
                if(!k.getColor().equals(color)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean xorColors(ArrayList<Piece> p){
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

    public static boolean areStraight(ArrayList<Piece> p){
        ArrayList<Piece> done = new ArrayList<>();
        for(int i=0; i<p.size()-1 ;i++){
            if(p.get(i)!=null && p.get(i+1)!=null){
                if(Math.abs(p.get(i).getNumber()-p.get(i+1).getNumber()) != 1 || done.contains(p.get(i)) ){
                    done.clear();
                    return false;
                }else{
                    done.add(p.get(i));
                }
            }
        }
        done.clear();
        return true;
    }

    public static boolean checkIfSame(ArrayList<Piece> pg){

        int num = pg.get(0).getNumber();
        for(Piece k : pg){
            if(k!=null && k.getNumber() != num){
                return false;
            }
        }
        return true;
    }

    public static boolean wasValid(ArrayList<Piece> pg){
        if(pg.size()>2){
            boolean current = isValidGrup(pg, false);
            if(!current){
                pg.remove(pg.size()-1);
                return isValidGrup(pg, false);
            }else{
                return current;
            }
        }else{
            return false;
        }
    }

    public static boolean isValidGrup(ArrayList<Piece> pg, boolean isSearching){

        if(pg.isEmpty()){
            return false;
        }
        if(checkIfSame(pg) && xorColors(pg)){
            if(!isSearching && pg.size()>=3){
                return true;
            }else if(isSearching){
                return true;
            }
        }

        if(!checkIfSame(pg) && areSameColor(pg) && areStraight(pg)){
            if(!isSearching && pg.size()>=3){
                return true;
            }else return isSearching;
        }

        return false;
    }

    public static boolean hasNumber(ArrayList<Piece> peces, int id){
        for(Piece p : peces){
            if(p!=null){
                if(p.getId() == id){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
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

    public static void getPieceGroups(ArrayList<Piece> peces, ArrayList<Piece> arr, int end, int groupId, int index){

       if( (!isValidGrup(arr, false) || index==end-1 ) && wasValid(arr)){
            Menu.printSeparator(arr.size());
            Menu.printHorizontalDeck(arr);
            Menu.printSeparator(arr.size());
            ArrayList<Piece> clone = (ArrayList<Piece>) arr.clone();
            if(groupId == -1){
                ArrayList<ArrayList<Piece>> kk = new ArrayList<>();
                kk.add(clone);
                groupId = id;
                resultats.put(groupId, kk);
                id++;
            }else{
                //piezas.removeAll(arr);
                resultats.get(groupId).add(clone);
            }

            ArrayList<Piece> newPeces = (ArrayList<Piece>) peces.clone();
            newPeces.removeAll(clone);
            allCombos.addAll(Collections.singleton(clone));
            arr = new ArrayList<>();
            getPieceGroups(newPeces, arr, newPeces.size(), groupId, 0);
            return;
        }


        for (int k=0; k<end; k++){
            if(k<peces.size()){
                if(!arr.contains(peces.get(k))){
                    Piece tmp = peces.get(k);
                    arr.add(tmp);
                    if(isValidGrup(arr, true) || k == end-2){
                        getPieceGroups(peces, arr, peces.size(), groupId, k);
                    }
                    arr.remove(tmp);
                }
            }
        }
    }


}

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Functions {

    public static ArrayList<Piece> allAvailable;


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



}

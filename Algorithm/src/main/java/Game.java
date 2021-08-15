import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {
    private ArrayList<Piece> myPieces = null;
    private ArrayList<PieceGroup> board = null;

    public ArrayList<Piece> getMyPieces() {
        return myPieces;
    }

    public void setMyPieces(ArrayList<Piece> myPieces) {
        this.myPieces = myPieces;
    }

    public Game withMyPieces(ArrayList<Piece> myPieces) {
        this.myPieces = myPieces;
        return this;
    }

    public ArrayList<PieceGroup> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<PieceGroup> board) {
        this.board = board;
    }

    public Game withBoard(ArrayList<PieceGroup> board) {
        this.board = board;
        return this;
    }

    @Override
    public String toString() {
        return "Game{" +
                "myPieces=" + myPieces +
                ", board=" + board +
                '}';
    }
}

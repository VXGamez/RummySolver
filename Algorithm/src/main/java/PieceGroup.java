import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PieceGroup {

    private ArrayList<Piece> pieces = null;
    private boolean isSame;

    public PieceGroup(ArrayList<Piece> pieces, boolean isSame) {
        this.pieces = pieces;
        this.isSame = isSame;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public PieceGroup withPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
        return this;
    }

    @Override
    public String toString() {
        return "PieceGroup{" +
                ", pieces=" + pieces +
                '}';
    }
}

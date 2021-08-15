import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PieceGroup {

    private Integer groupNumber;
    private ArrayList<Piece> pieces = null;

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public PieceGroup withGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
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
                "groupNumber=" + groupNumber +
                ", pieces=" + pieces +
                '}';
    }
}

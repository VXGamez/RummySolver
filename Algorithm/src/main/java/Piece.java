import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Piece {

    private Integer number;
    private String color;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Piece withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Piece withColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "number=" + number +
                ", color='" + color + '\'' +
                '}';
    }
}
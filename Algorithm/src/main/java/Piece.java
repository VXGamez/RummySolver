import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Piece {

    private int id;
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
    public boolean equals(Object obj) {
        if(obj instanceof Piece){
            Piece element = (Piece) obj;
            if(this.number.equals(element.number) && this.color.equals(element.color)){
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String num = " " + number;
        if(number.toString().length()==1){
            num+=" ";
        }
        if(number == 14){
            num = "ʘ‿ʘ";
        }
        return ".___.\n|" + Menu.colors.get(color) + num + Menu.ANSI_RESET + "|\n|___|";
    }
}
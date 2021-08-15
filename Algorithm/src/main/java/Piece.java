import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

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
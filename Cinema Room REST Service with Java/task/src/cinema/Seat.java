package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Seat {

    private int row;
    private int column;
    private int price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Token token;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean purchased = false;

    public Seat() {
    }

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public UUID getToken() {
        return token.getToken();
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
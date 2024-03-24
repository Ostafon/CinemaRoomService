package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CinemaInfo {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public CinemaInfo(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                seats.add(new Seat(i, j, (i <= 4) ? 10 : 8));
            }
        }
    }

    @JsonProperty("rows")
    public int getTotalRows() {
        return rows;
    }

    @JsonProperty("columns")
    public int getTotalColumns() {
        return columns;
    }

    @JsonProperty("seats")
    public List<Seat> getAvailableSeats() {
        List availableSeats = seats.stream()
                .filter(s -> !s.isPurchased()).collect(Collectors.toList());
        return availableSeats;
    }

    public Map purchaseSeat(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                if (!seat.isPurchased()) {
                    Token token = new Token();
                    seat.setToken(token);
                    seat.setPurchased(true);
                    return Map.of("token", seat.getToken(), "ticket", seat);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
                }

            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
    }

    public Map returnSeat(Token token) {
        for (Seat seat : seats) {
            if (seat.isPurchased() && seat.getToken().equals(token.getToken())) {
                seat.setToken(null);
                seat.setPurchased(false);
                return Map.of("ticket", seat);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");
    }

    public Map getStats(String password) {
        if (password != null && password.equals("super_secret")) {
            long currentIncome = seats.stream()
                    .filter(s -> s.isPurchased())
                    .reduce(0, (subtotal, element) -> subtotal + element.getPrice(), Integer::sum);
            long number_of_available_seats = seats.stream()
                    .filter(s -> !s.isPurchased()).count();

            long number_of_purchased_tickets = seats.stream()
                    .filter(s -> s.isPurchased()).count();

            return Map.of("income", currentIncome,
                    "available", number_of_available_seats,
                    "purchased", number_of_purchased_tickets);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");
    }
}



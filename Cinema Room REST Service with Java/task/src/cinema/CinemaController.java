package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class CinemaController {

    int rows = 9;
    int columns = 9;

    CinemaInfo cinema = new CinemaInfo(rows, columns);

    @GetMapping("/seats")
    public CinemaInfo getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Map purchaseSeat(@RequestBody Seat seatToPurchase) {

        return cinema.purchaseSeat(seatToPurchase.getRow(), seatToPurchase.getColumn());
    }

    @PostMapping("/return")
    public Map returnSeat(@RequestBody Token token) {
        return cinema.returnSeat(token);
    }

    @GetMapping ("/stats")
    public Map getStats(@RequestParam (required = false) String password){
        return cinema.getStats(password);
    }
}



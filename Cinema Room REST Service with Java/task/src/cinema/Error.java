package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class Error {
    static class ErrorMessage {
        private final String error;

        public ErrorMessage(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> returnOnlyReason(ResponseStatusException e) {
        return new ResponseEntity<>( new ErrorMessage( e.getReason() ), e.getStatusCode() );
    }
}
package cinema;

import java.util.UUID;

public class Token {
    private final UUID token;

    public Token() {
        UUID uuid = UUID.randomUUID();
        this.token = uuid;
    }

    public UUID getToken() {
        return token;
    }
}
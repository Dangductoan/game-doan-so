package cybersoft.javabackend.java18.gamedoanso.model;

import java.time.LocalDateTime;

public class Guess {
    private final int value;
    private final String gameSession;
    private final int result;
    private final LocalDateTime timestamp;

    public Guess(int value, String gameSession, int result) {
        this.value = value;
        this.gameSession = gameSession;
        this.result = result;
        timestamp = LocalDateTime.now();
    }

    public int getValue() {
        return this.value;
    }

    public int getResult() {
        return this.result;
    }

    public String getGameSession() {
        return this.gameSession;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

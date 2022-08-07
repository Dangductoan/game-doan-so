package cybersoft.javabackend.java18.gamedoanso.model;

import java.time.LocalDateTime;

public class Guess {
    private final int value;
    private final String gameSession;
    private final LocalDateTime timestamp;
    private int result;

    public Guess(int value, String gameSession, int result) {
        this.value = value;
        this.gameSession = gameSession;
        this.result = result;
        timestamp = LocalDateTime.now();
    }

    public Guess(int value, String gameSession, LocalDateTime timestamp) {
        this.value = value;
        this.gameSession = gameSession;
        this.timestamp = timestamp;
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

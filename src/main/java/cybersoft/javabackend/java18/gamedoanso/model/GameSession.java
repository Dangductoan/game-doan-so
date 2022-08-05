package cybersoft.javabackend.java18.gamedoanso.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSession implements Serializable {
    private static int startId = 1;
    private static Random random = null;
    private final String id;
    private final int targetNumber;
    private final List<Guess> guess;
    private final LocalDateTime startTime;
    private final String username; // username
    private LocalDateTime endTime;
    private boolean isCompleted;
    private boolean isActive;

    public GameSession(String username) {
        this.id = "GAME" + String.format("%05d", startId++);
        this.targetNumber = getRandomInt();
        this.guess = new ArrayList<>();
        this.startTime = LocalDateTime.now();
        this.username = username;
    }

    private static int getRandomInt() {
        if (random == null)
            random = new Random();

        return random.nextInt(1000 - 1) + 1;
    }

    public int getTargetNumber() {
        return this.targetNumber;
    }

    public String getId() {
        return id;
    }

    public List<Guess> getGuess() {
        return guess;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("[id: %s, active: %s]", this.id, this.isActive);
    }
}

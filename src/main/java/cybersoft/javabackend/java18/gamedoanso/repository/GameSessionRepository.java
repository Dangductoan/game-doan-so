package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.GameSession;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.util.List;

public class GameSessionRepository {
    private final List<GameSession> gameSessions;

    public GameSessionRepository() {
        gameSessions = GameStoreHolder.getStore().getGameSessions();
    }

    public void save(GameSession gameSession) {
        gameSessions.add(gameSession);
    }

    public List<GameSession> findByUsername(String username) {
        return gameSessions.stream()
                .filter(g -> g.getUsername().equals(username))
                .toList();
    }

    public GameSession findById(String id) {
        return gameSessions.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

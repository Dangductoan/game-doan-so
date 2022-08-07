package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.GameSession;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;

public class GameSessionRepository extends AbstractRepository<GameSession> {
    private final List<GameSession> gameSessions;

    public GameSessionRepository() {
        gameSessions = GameStoreHolder.getStore().getGameSessions();
    }

    public void save(GameSession gameSession) {
        executeUpdate(connection -> {
            String query = """
                    insert into game_session
                    (id, target, start_time, is_completed, is_active, username)
                    values(?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameSession.getId());
            statement.setInt(2, gameSession.getTargetNumber());
            statement.setTimestamp(3, Timestamp.from(
                    gameSession.getStartTime().toInstant(ZoneOffset.of("+07:00")))
            );
            statement.setInt(4, gameSession.getIsCompleted() ? 1 : 0);
            statement.setInt(5, gameSession.isActive() ? 1 : 0);
            statement.setString(6, gameSession.getUsername());

            return statement.executeUpdate();
        });
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

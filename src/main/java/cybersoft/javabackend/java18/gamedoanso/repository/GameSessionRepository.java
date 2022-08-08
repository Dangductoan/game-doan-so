package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.GameSession;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
        return executeQuery(connection -> {
            String query = """
                        select id,  target, start_time, end_time, is_completed, is_active, username
                        from game_session
                        where username = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            List<GameSession> sessions = new ArrayList<>();

            while (resultSet.next()) {
                sessions.add(new GameSession()
                        .id(resultSet.getString("id"))
                        .targetNumber(resultSet.getInt("target"))
                        .startTime(getDateTimeFromResultSet("start_time", resultSet))
                        .endTime(getDateTimeFromResultSet("end_time", resultSet))
                        .isCompleted(resultSet.getInt("is_completed") == 1)
                        .isActive(resultSet.getInt("is_active") == 1)
                        .username(resultSet.getString("username")));
            }

            return sessions;
        });
    }

    public GameSession findById(String id) {
        return executeQuerySingle(connection -> {
            String query = """
                        select id,  target, start_time, end_time, is_completed, is_active, username
                        from game_session
                        where id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                return null;

            return new GameSession()
                    .id(resultSet.getString("id"))
                    .targetNumber(resultSet.getInt("target"))
                    .startTime(getDateTimeFromResultSet("start_time", resultSet))
                    .endTime(getDateTimeFromResultSet("end_time", resultSet))
                    .isCompleted(resultSet.getInt("is_completed") == 1)
                    .isActive(resultSet.getInt("is_active") == 1)
                    .username(resultSet.getString("username"));
        });
    }

    public void deactivateAllGames(String username) {
        executeUpdate(connection -> {
            String query = """
                    update game_session
                    set is_active = 0
                    where username = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            return statement.executeUpdate();
        });
    }

    public void completeGame(String sessionId) {
        executeUpdate(connection -> {
            String query = """
                    update game_session
                    set
                        is_completed = 1,
                        is_active = 0
                    where id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sessionId);

            return statement.executeUpdate();
        });
    }

    private LocalDateTime getDateTimeFromResultSet(String columnName, ResultSet resultSet) {
        Timestamp time = null;

        try {
            time = resultSet.getTimestamp(columnName);
        } catch (SQLException e) {
            return null;
        }

        if (time == null)
            return null;

        return time.toLocalDateTime();
    }
}

package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.Guess;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GuessRepository extends AbstractRepository<Guess> {
    private final List<Guess> guesses;

    public GuessRepository() {
        guesses = GameStoreHolder.getStore().getGuesses();
    }

    public Collection<? extends Guess> findBySession(String session) {
        return executeQuery(connection -> {
            String query = """
                    select value, moment, session_id
                    from guess
                    where session_id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, session);

            ResultSet resultSet = statement.executeQuery();

            List<Guess> guesses = new LinkedList<>();
            while (resultSet.next()) {
                Guess newGuess = new Guess(
                        resultSet.getInt("value"),
                        resultSet.getString("session_id"),
                        resultSet.getDate("moment").toLocalDate().atStartOfDay()
                );
                guesses.add(newGuess);
            }

            return guesses;
        });
    }
}

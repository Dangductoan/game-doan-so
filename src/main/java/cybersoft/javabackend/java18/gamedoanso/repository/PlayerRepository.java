package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.jdbc.MySqlConnection;
import cybersoft.javabackend.java18.gamedoanso.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository {

    public PlayerRepository() {

    }

    public Player findByUsername(String username) {
        /* JDBC Connection */
        // create a connection to database
        try (Connection connection = MySqlConnection.getConnection()) {
            // write a query to find the player by username
            String query = """
                    select username, password, name
                    from player
                    where username = ?
                    """;
            // create a prepared statement to execute the query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet results = statement.executeQuery();

            // get result from result set
            if (results.next()) {
                return new Player(
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("name")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Error while connecting to database: %s",
                            e.getMessage())
            );
        }
    }

    public boolean existedByUsername(String username) {
        // create a connection to database
        try (Connection connection = MySqlConnection.getConnection()) {
            // write a query to find the player by username
            String query = """
                    select username
                    from player
                    where username = ?
                    """;
            // create a prepared statement to execute the query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet results = statement.executeQuery();

            // get result from result set
            return results.next();
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Error while connecting to database: %s",
                            e.getMessage())
            );
        }
    }

    public void save(Player newUser) {
        // get a connection to database
        try (Connection connection = MySqlConnection.getConnection()) {
            // write a query to save new player
            String query = """
                    insert into player(username, password, name)
                    values(?, ?, ?)
                    """;
            // create a statement to execute the query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Error while connecting to database: %s",
                            e.getMessage())
            );
        }
    }
}

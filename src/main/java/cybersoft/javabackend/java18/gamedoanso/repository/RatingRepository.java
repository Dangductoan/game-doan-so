package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.Rating;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RatingRepository extends AbstractRepository<Rating>{
   public List<Rating> sortByAmountAndTime() {
       final String query = """
               SELECT k1.amount, k1.session_id,k3.username,timestampdiff(SECOND,k3.start_time,k2.moment) as time
               FROM 
               (SELECT COUNT(session_id) as amount, session_id
               FROM guess
               GROUP BY session_id
                ) k1
               LEFT JOIN
               (select session_id,moment from guess where guess.result = 0 ) k2
               on (k1.session_id = k2.session_id)
               LEFT JOIN
               (select id,start_time,username from game_session ) k3
               on (k2.session_id = k3.id)
               ORDER BY amount,
               time
               """;
       return executeQuery(connection -> {
           PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery();
           List<Rating> ratings = new ArrayList<>();
           while(resultSet.next()) {
              Rating rating = new Rating();
              rating.setAmount(resultSet.getInt("amount"));
              rating.setSession_id(resultSet.getString("session_id"));
              rating.setUsername(resultSet.getString("username"));
              rating.setTime(resultSet.getString("time"));

              ratings.add(rating);
           }
           return ratings;
       });
   }
    private LocalDateTime getDateTimeFromResultSet(String columnName, ResultSet resultSet) {
        Timestamp time;

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

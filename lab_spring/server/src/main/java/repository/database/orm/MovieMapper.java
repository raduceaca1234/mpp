package repository.database.orm;

import domain.Movie;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MovieMapper implements RowMapper<Movie> {
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie(
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getInt("rating"));
        movie.setId(rs.getLong("id"));

        return movie;
    }
}

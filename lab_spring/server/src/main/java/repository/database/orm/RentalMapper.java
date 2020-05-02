package repository.database.orm;


import domain.Rental;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RentalMapper implements RowMapper<Rental> {
    public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rental rental = new Rental(
                rs.getLong("client"),
                rs.getLong("movie"));
        rental.setId(rs.getLong("id"));

        return rental;
    }
}

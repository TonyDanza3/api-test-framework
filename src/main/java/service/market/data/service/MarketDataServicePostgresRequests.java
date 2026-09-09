package service.market.data.service;

import dto.response.instruments.Instrument;
import dto.response.instruments.Instruments;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static core.Postgres.getDbConnection;

public class MarketDataServicePostgresRequests {

    public static Instruments getInstruments() {
        String getInstrumentsRequest = "select * from instruments;";
        Connection connection = getDbConnection();
        List<Instrument> instruments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getInstrumentsRequest);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                instruments.add(new Instrument(
                        resultSet.getString("id"),
                        resultSet.getString("symbol"),
                        resultSet.getString("name"),
                        resultSet.getString("asset_class"),
                        resultSet.getObject("created_at", OffsetDateTime.class),
                        resultSet.getObject("updated_at", OffsetDateTime.class)
                ));
            }
            return new Instruments(instruments, null, null, null);

        } catch (SQLException e) {
            throw new RuntimeException("Could not execute request \"" + getInstrumentsRequest + "\" because of the exception: " + e);
        }
    }
}

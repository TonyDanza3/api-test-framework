package service.market.data.service;

import dto.response.instruments.InstrumentResponseBody;
import dto.response.instruments.InstrumentsResponseBody;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static core.Postgres.getDbConnection;

public class MarketDataServicePostgresRequests {

    public static InstrumentsResponseBody getInstruments() {
        String getInstrumentsRequest = "select * from instruments;";
        Connection connection = getDbConnection();
        List<InstrumentResponseBody> instruments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getInstrumentsRequest);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                instruments.add(new InstrumentResponseBody(
                        resultSet.getString("id"),
                        resultSet.getString("symbol"),
                        resultSet.getString("name"),
                        resultSet.getString("asset_class"),
                        resultSet.getObject("created_at", OffsetDateTime.class),
                        resultSet.getObject("updated_at", OffsetDateTime.class)
                ));
            }
            return new InstrumentsResponseBody(instruments, null, null, null);

        } catch (SQLException e) {
            throw new RuntimeException("Could not execute request \"" + getInstrumentsRequest + "\" because of the exception: " + e);
        }
    }

    public static InstrumentResponseBody getInstrumentById(String id) {
        String getInstrumentsRequest = "select * from instruments where id = ?;";
        Connection connection = getDbConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(getInstrumentsRequest);
            statement.setObject(1, UUID.fromString(id));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new InstrumentResponseBody(
                        resultSet.getString("id"),
                        resultSet.getString("symbol"),
                        resultSet.getString("name"),
                        resultSet.getString("asset_class"),
                        resultSet.getObject("created_at", OffsetDateTime.class),
                        resultSet.getObject("updated_at", OffsetDateTime.class)
                );

        } catch (SQLException e) {
            throw new RuntimeException("Could not execute request \"" + getInstrumentsRequest + "\" because of the exception: " + e);
        }
    }
}

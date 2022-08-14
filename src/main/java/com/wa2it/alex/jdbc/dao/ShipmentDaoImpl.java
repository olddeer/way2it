package com.wa2it.alex.jdbc.dao;


import com.wa2it.alex.jdbc.config.DatabaseConfig;
import com.wa2it.alex.jdbc.entity.ShipmentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDaoImpl implements ShipmentDao {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ShipmentEntity> getAllShipments() throws SQLException {

        List<ShipmentEntity> shipmentEntities;

        try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM SHIPMENT")
                .executeQuery()) {

            shipmentEntities = new ArrayList<>();

            while (resultSet.next()) {

                shipmentEntities.add(getShipmentFromResultSet(resultSet));
            }
        }

        return shipmentEntities;
    }

    @Override
    public ShipmentEntity getShipmentById(Integer id) throws SQLException {
        ShipmentEntity shipmentEntity;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM SHIPMENT WHERE ID = ?")) {
            statement.setInt(1, id);

            shipmentEntity = getShipmentFromResultSet(statement.executeQuery());

        }

        return shipmentEntity;
    }

    @Override
    public void addShipment(Integer vehicleId, Integer cargoId, String destination) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO SHIPMENT.SHIPMENT (vehicle_id, cargo_id, destination) VALUES (?, ?, ?)");
        statement.setInt(1, vehicleId);
        statement.setInt(2, cargoId);
        statement.setString(3, destination);
        statement.execute();
        statement.close();
    }

    @Override
    public void updateShipment(Integer shipmentId, Integer vehicleId, Integer cargoId, String destination)
        throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE SHIPMENT.SHIPMENT "
            + " SET vehicle_id = ?, cargo_id = ?, destination = ? WHERE id = ?");
        statement.setInt(1, vehicleId);
        statement.setInt(2, cargoId);
        statement.setString(3, destination);
        statement.setInt(4, shipmentId);
        statement.execute();
        statement.close();

    }

    @Override
    public ShipmentEntity getShipmentByCargoId(Integer cargoId) throws SQLException {
        ShipmentEntity shipmentEntity;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM SHIPMENT WHERE CARGO_ID = ?")) {
            statement.setInt(1, cargoId);

            shipmentEntity = getShipmentFromResultSet(statement.executeQuery());

        }

        return shipmentEntity;
    }

    private ShipmentEntity getShipmentFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer cargoId = resultSet.getInt("cargo_id");
        Integer vehicleId = resultSet.getInt("vehicle_id");
        String destination = resultSet.getString("destination");

        ShipmentEntity shipmentEntity = new ShipmentEntity();
        shipmentEntity.setId(id);
        shipmentEntity.setDestination(destination);
        shipmentEntity.setVehicleId(vehicleId);
        shipmentEntity.setCargoId(cargoId);
        return shipmentEntity;
    }
}

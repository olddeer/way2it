package com.wa2it.alex.jdbc.dao;

import com.wa2it.alex.jdbc.config.DatabaseConfig;
import com.wa2it.alex.jdbc.entity.CargoEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImpl implements CargoDao {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CargoEntity> getAllCargos() {
        List<CargoEntity> cargoEntities = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CARGO");
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                double volume = resultSet.getDouble("volume");
                cargoEntities.add(new CargoEntity(id, description, volume));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargoEntities;
    }

    @Override
    public CargoEntity getCargoById(Integer cargoId, String descriptionParam) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM CARGO WHERE id = ? AND description = ?" )) {
            statement.setInt(1, cargoId);
            statement.setString(2, descriptionParam);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString(2);
                int id = resultSet.getInt(1);
                double volume = resultSet.getDouble(3);
                return new CargoEntity(id, description, volume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCargo(String description, Double volume) {

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO CARGO (description, volume) VALUES (?,?)")) {
            preparedStatement.setString(1, description);
            preparedStatement.setDouble(2, volume);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCargo(Integer cargoId, String description, Double volume) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE CARGO SET description = ?, volume = ? WHERE id = ?")) {
            preparedStatement.setString(1, description);
            preparedStatement.setDouble(2, volume);
            preparedStatement.setDouble(3, cargoId);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

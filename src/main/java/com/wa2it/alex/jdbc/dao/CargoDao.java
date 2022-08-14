package com.wa2it.alex.jdbc.dao;

/// CRUD

// C - create
// R - read
// U - update
// d - delete

import com.wa2it.alex.jdbc.entity.CargoEntity;

import java.sql.SQLException;
import java.util.List;

public interface CargoDao {

    List<CargoEntity> getAllCargos() throws SQLException;

    CargoEntity getCargoById(Integer cargoId, String test) throws SQLException;

    void addCargo(String description, Double volume) throws SQLException;

    void updateCargo(Integer cargoId, String description, Double volume);

}

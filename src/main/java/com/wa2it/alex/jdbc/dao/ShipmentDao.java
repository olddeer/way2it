package com.wa2it.alex.jdbc.dao;


import com.wa2it.alex.jdbc.entity.ShipmentEntity;

import java.sql.SQLException;
import java.util.List;

public interface ShipmentDao {

    List<ShipmentEntity> getAllShipments() throws SQLException;

    ShipmentEntity getShipmentById(Integer id) throws SQLException;

    void addShipment(Integer vehicleId, Integer cargoId, String destination) throws SQLException;

    void updateShipment(Integer shipmentId, Integer vehicleId, Integer cargoId, String destination) throws SQLException;

    ShipmentEntity getShipmentByCargoId(Integer id) throws SQLException;

}

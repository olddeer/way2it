package com.wa2it.alex.jdbc;

import com.wa2it.alex.jdbc.dao.CargoDao;
import com.wa2it.alex.jdbc.dao.CargoDaoImpl;
import com.wa2it.alex.jdbc.dao.ShipmentDao;
import com.wa2it.alex.jdbc.dao.ShipmentDaoImpl;
import com.wa2it.alex.jdbc.dao.VehicleDao;
import com.wa2it.alex.jdbc.dao.VehicleDaoImpl;
import com.wa2it.alex.jdbc.entity.CargoEntity;
import com.wa2it.alex.jdbc.entity.VehicleEntity;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CargoDao cargoDao = new CargoDaoImpl();

        cargoDao.getAllCargos().forEach(System.out::println);
        System.out.println(cargoDao.getCargoById(11, "test"));
        cargoDao.addCargo("table", 10d);
        System.out.println(cargoDao.getCargoById(45, "table"));
        cargoDao.updateCargo(45, "phone", 1d);
        CargoEntity updatedCargo = cargoDao.getCargoById(45, "phone");


        VehicleDao vehicleDao = new VehicleDaoImpl();
        VehicleEntity vehicle = vehicleDao.addVehicle("BMW");

        ShipmentDao shipmentDao = new ShipmentDaoImpl();

        shipmentDao.addShipment(vehicle.getId(), updatedCargo.getId(), "Kyiv");


    }

}

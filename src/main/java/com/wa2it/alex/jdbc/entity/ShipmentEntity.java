package com.wa2it.alex.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentEntity {

    private Integer id;
    private Integer vehicleId;
    private Integer cargoId;
    private String destination;
}

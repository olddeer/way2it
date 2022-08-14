package com.wa2it.alex.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class CargoEntity {

    private Integer id;
    private String description;
    private Double volume;

}

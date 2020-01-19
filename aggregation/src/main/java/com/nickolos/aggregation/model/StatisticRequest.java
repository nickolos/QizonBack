package com.nickolos.aggregation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticRequest {

    private Long id_game;

    private String name;

}

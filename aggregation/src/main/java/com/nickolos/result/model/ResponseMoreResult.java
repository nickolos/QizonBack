package com.nickolos.result.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseMoreResult {

    private int score;

    private String name;

    private String info;
}

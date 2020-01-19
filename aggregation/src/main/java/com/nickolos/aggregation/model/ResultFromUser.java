package com.nickolos.aggregation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultFromUser {

    private Long id_game;

    private int number;

    private String uid;

    private String name;

    private String answer_user;
}

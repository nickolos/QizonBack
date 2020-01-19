package com.nickolos.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAnswerFifthRound {

    private Long id_game;

    private String answer;

    private int number;

}

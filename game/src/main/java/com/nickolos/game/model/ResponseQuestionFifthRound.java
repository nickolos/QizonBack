package com.nickolos.game.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
public class ResponseQuestionFifthRound {

    private int number;

    private String question;

    private Long id_game;

    private String file;



}

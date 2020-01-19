package com.nickolos.game.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Round5", schema = "round5")
public class Round5 {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "question",columnDefinition="TEXT")
    private String question;

    @Column(name = "answer",columnDefinition="TEXT")
    private String answer;

    @Column(name = "path_to_image")
    private String path_to_image;


    @Column(name = "timer")
    private  Long timer =60*1000L;

}

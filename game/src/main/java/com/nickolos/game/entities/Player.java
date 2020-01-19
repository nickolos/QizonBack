package com.nickolos.game.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Player", schema = "player")
public class Player {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Long id ;

    @Column(name = "UID")
    private String uid;

    @Column(name = "name")
    private String name;



}

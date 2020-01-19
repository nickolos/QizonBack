package com.nickolos.result.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullResultId implements Serializable {


    @Column(name = "id_game")
    private Long id_game;


    @Column(name = "UID")
    private String uid;

    @Column(name = "name")
    private String name;
}

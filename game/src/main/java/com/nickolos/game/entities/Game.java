package com.nickolos.game.entities;


import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Game", schema = "game")
@TypeDefs({
@TypeDef(
        name = "int-array",
        typeClass = IntArrayType.class
)
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Type(
            type = "int-array"
    )
    @Column(name = "fifth_round",
            columnDefinition = "int[]"
    )
    private int[] fifth_round;

    @Column(name = "game_status")
    private boolean game_status;

    @Column(name = "time")
    private Date time;


}

package com.nickolos.result.entities;


import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
@Table(name = "fullresult", schema = "result")
public class FullResult {

    @EmbeddedId
    private FullResultId id;


    @Type( type = "string-array" )
    @Column(name = "answer",  columnDefinition = "text []")
    private String [] answer;

    @Column(name = "info")
    private String info;


    @Column(name = "score")
    private int score;

    @Column(name = "place")
    private int place;


}

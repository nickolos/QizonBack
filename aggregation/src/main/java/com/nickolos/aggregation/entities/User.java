package com.nickolos.aggregation.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "usr", schema = "usr")
public class User {
    @Id
    private String id;

    @UniqueElements
    private String name;

    private String email;

    private int rating;

    private int fp_count;
}

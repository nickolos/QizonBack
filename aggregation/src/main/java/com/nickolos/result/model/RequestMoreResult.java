package com.nickolos.result.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMoreResult {

    private String name;

    private Long id_game;

    private String uid;

}

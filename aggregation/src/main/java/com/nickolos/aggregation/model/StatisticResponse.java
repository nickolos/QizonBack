package com.nickolos.aggregation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticResponse {

    private String UID;

    private int rating;

    private int fp_count;
}

package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Carry implements Serializable {

    private String bestBuy;

    private String bestSell;

    private String lastPrice;
}

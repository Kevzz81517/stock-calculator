package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Price implements Serializable {

    private Integer bestBuy;

    private String bestSell;

    private String lastPrice;
}

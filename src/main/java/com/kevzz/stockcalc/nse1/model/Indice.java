package com.kevzz.stockcalc.nse1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Indice {

    private String name;
    private String lastPrice;
    private String change;
    private String pChange;
    private String imgFileName;
}

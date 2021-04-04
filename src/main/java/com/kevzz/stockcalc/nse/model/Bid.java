package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Bid implements Serializable {

    private String price;
    
    private String quantity;
}

package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Stock implements Serializable {

    private Metadata metadata;

    private String underlyingValue;

    private String volumeFreezeQuantity;

    private MarketDeptOrderBook marketDeptOrderBook;
}

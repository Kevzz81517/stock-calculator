package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OtherInfo implements Serializable {

    private String settlementPrice;

    private String dailyvolatility;

    private String annualisedVolatility;

    private String impliedVolatility;

    private String clientWisePositionLimits;

    private String marketWidePositionLimits;
}

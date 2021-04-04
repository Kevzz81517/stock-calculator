package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TradeInfo implements Serializable {

    private String tradedVolume;

    private String value;

    private String vmap;

    private String premiumTurnover;

    private String openInterest;

    private String changeinOpenInterest;

    private String pchangeinOpenInterest;

    private String marketLot;
}

package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Metadata implements Serializable {

    private String instrumentType;

    private String expiryDate;

    private String optionType;

    private String strikePrice;

    private String identifier;

    private String openPrice;

    private String highPrice;

    private String lowPrice;

    private String closePrice;

    private String prevClose;

    private String lastPrice;

    private String change;

    private String pChange;

    private String numberOfContractsTraded;

    private String totalTurnover;
}

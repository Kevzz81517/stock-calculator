package com.kevzz.stockcalc.nse1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class IndexOption implements Serializable {
    private String annualisedVolatility;
    private String bestBuy;
    private String totalSellQuantity;
    private String vwap;
    private String clientWisePositionLimits;
    private String optionType;
    private String highPrice;
    private String dailyVolatility;
    private String bestSell;
    private String marketLot;
    private String sellQuantity5;
    private String marketWidePositionLimits;
    private String sellQuantity4;
    private String sellQuantity3;
    private String sellQuantity2;
    private String underlying;
    private String sellQuantity1;
    private String pChange;
    private String premiumTurnover;
    private String totalBuyQuantity;
    private String turnoverinRsLakhs;
    private String changeinOpenInterest;
    private String strikePrice;
    private String openInterest;
    private String buyPrice2;
    private String buyPrice1;
    private String openPrice;
    private String prevClose;
    private String expiryDate;
    private String lowPrice;
    private String buyPrice4;
    private String buyPrice3;
    private String buyPrice5;
    private String numberOfContractsTraded;
    private String instrumentType;
    private String sellPrice1;
    private String sellPrice2;
    private String sellPrice3;
    private String sellPrice4;
    private String sellPrice5;
    private String change;
    private String pchangeinOpenInterest;
    private String ltp;
    private String impliedVolatility;
    private String underlyingValue;
    private String buyQuantity4;
    private String buyQuantity3;
    private String buyQuantity2;
    private String buyQuantity1;
    private String buyQuantity5;
    private String settlementPrice;
    private String closePrice;
    private String lastPrice;

}

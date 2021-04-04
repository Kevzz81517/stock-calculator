package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MarketDeptOrderBook implements Serializable {

    private String totalBuyQuantity;

    private String totalSellQuantity;

    private List<Bid> bid = null;

    private List<Ask> ask = null;

    private CarryOfCost carryOfCost;

    private TradeInfo tradeInfo;

    private OtherInfo otherInfo;
}

package com.kevzz.stockcalc.nse1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "index_option_entities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexOptionEntity extends IndexOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String niftyPrice;

    public IndexOptionEntity(IndexOption indexOption, String niftyPrice) {

        super(
                indexOption.getAnnualisedVolatility(),
                indexOption.getBestBuy(),
                indexOption.getTotalSellQuantity(),
                indexOption.getVwap(),
                indexOption.getClientWisePositionLimits(),
                indexOption.getOptionType(),
                indexOption.getHighPrice(),
                indexOption.getDailyVolatility(),
                indexOption.getBestSell(),
                indexOption.getMarketLot(),
                indexOption.getSellQuantity5(),
                indexOption.getMarketWidePositionLimits(),
                indexOption.getSellQuantity4(),
                indexOption.getSellQuantity3(),
                indexOption.getSellQuantity2(),
                indexOption.getUnderlying(),
                indexOption.getSellQuantity1(),
                indexOption.getPChange(),
                indexOption.getPremiumTurnover(),
                indexOption.getTotalBuyQuantity(),
                indexOption.getTurnoverinRsLakhs(),
                indexOption.getChangeinOpenInterest(),
                indexOption.getStrikePrice(),
                indexOption.getOpenInterest(),
                indexOption.getBuyPrice2(),
                indexOption.getBuyPrice1(),
                indexOption.getOpenPrice(),
                indexOption.getPrevClose(),
                indexOption.getExpiryDate(),
                indexOption.getLowPrice(),
                indexOption.getBuyPrice4(),
                indexOption.getBuyPrice3(),
                indexOption.getBuyPrice5(),
                indexOption.getNumberOfContractsTraded(),
                indexOption.getInstrumentType(),
                indexOption.getSellPrice1(),
                indexOption.getSellPrice2(),
                indexOption.getSellPrice3(),
                indexOption.getSellPrice4(),
                indexOption.getSellPrice5(),
                indexOption.getChange(),
                indexOption.getPchangeinOpenInterest(),
                indexOption.getLtp(),
                indexOption.getImpliedVolatility(),
                indexOption.getUnderlyingValue(),
                indexOption.getBuyQuantity4(),
                indexOption.getBuyQuantity3(),
                indexOption.getBuyQuantity2(),
                indexOption.getBuyQuantity1(),
                indexOption.getBuyQuantity5(),
                indexOption.getSettlementPrice(),
                indexOption.getClosePrice(),
                indexOption.getLastPrice()
        );

        this.niftyPrice = niftyPrice;

    }
}

package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IndexDerivative implements Serializable {

    private IndexDerivativeInfo info;

    private String underlyingValue;

    private String vfq;

    private String futTimestamp;

    private String optTimestamp;

    private List<Stock> stocks = null;

    private List<String> strikePrices = null;

    private List<String> expiryDates = null;
}

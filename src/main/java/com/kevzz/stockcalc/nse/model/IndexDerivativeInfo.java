package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IndexDerivativeInfo implements Serializable {

    private String symbol;

    private String companyName;

    private String identifier;

    private List<Object> activeSeries;

    private List<Object> debtSeries;

    private Boolean isFNOSec;

    private Boolean isCASec;

    private Boolean isSLBSec;

    private Boolean isDebtSec;

    private Boolean isSuspended;
}

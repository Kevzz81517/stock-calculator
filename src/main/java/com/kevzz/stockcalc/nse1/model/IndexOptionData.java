package com.kevzz.stockcalc.nse1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexOptionData implements Serializable {
    private String valid;
    private String tradedDate;
    private String eqLink;
    private List<IndexOption> data;
    private String companyName;
    private String lastUpdateTime;
    private Object isinCode;
    private String ocLink;
}

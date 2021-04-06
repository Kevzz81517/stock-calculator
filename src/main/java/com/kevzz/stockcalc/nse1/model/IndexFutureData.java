package com.kevzz.stockcalc.nse1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexFutureData {

    private String valid;

    private String tradedDate;

    private String eqLink;

    private List<IndexFutureDataNum> data;

    private String companyName;

    private String lastUpdateTime;

    private String ocLink;
}

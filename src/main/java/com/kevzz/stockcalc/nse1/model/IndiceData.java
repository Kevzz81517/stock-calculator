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
public class IndiceData implements Serializable {
    private Long preClose;
    private Long code;
    private List<Indice> data;
    private Long mktOpen;
    private Long corrClose;
    private Long preOpen;
    private Long corrOpen;
    private String haltedStatus;
    private String time;
    private Long mktClose;
    private String status;

}

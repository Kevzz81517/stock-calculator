package com.kevzz.stockcalc.nse.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IndiceData implements Serializable {

    private String identifier;

    private String name;

    private List<List<String>> grapthData;

    private String closePrice;

}

package com.kevzz.stockcalc.nse1.service.indices.derivative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevzz.stockcalc.nse1.model.IndexOptionData;
import com.kevzz.stockcalc.nse1.model.Indice;
import com.kevzz.stockcalc.nse1.model.IndiceData;

import java.util.HashMap;

public interface DataService {
    IndiceData getIndices();

    Indice getIndice(String name);

    IndexOptionData getIndexOptions(String name, String expiry, String optionType, String strikePrice);

    HashMap<String, Object> evaluateIndicesData(int records);
}

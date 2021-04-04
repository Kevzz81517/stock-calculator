package com.kevzz.stockcalc.nse.service.indices.derivative;

import com.kevzz.stockcalc.nse.model.IndexDerivative;
import com.kevzz.stockcalc.nse.model.IndiceData;

import java.util.HashMap;

public interface DataService {

    IndiceData getIndice(String name);

    IndexDerivative getIndiceDerivateData(String indiceName);

    HashMap<String, Object> evaluateIndicesData();
}

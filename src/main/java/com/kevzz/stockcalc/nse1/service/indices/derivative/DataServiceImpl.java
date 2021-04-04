package com.kevzz.stockcalc.nse1.service.indices.derivative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevzz.stockcalc.exception.ApplicationException;
import com.kevzz.stockcalc.nse1.fetch.RestFetcher;
import com.kevzz.stockcalc.nse1.model.IndexOption;
import com.kevzz.stockcalc.nse1.model.IndexOptionData;
import com.kevzz.stockcalc.nse1.model.Indice;
import com.kevzz.stockcalc.nse1.model.IndiceData;
import com.kevzz.stockcalc.util.DateFormattingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private ObjectMapper objectMapper;

    public DataServiceImpl() {
    }

    public IndiceData getIndices() {
        return (IndiceData) RestFetcher.restClient.getForEntity("https://www1.nseindia.com/homepage/Indices1.json", IndiceData.class, new Object[0]).getBody();
    }

    public Indice getIndice(String name) {
        IndiceData indiceData = this.getIndices();

        try {
            return (Indice) indiceData.getData().stream().filter((indice) -> {
                return indice.getName().equals(name);
            }).findFirst().get();
        } catch (NoSuchElementException var4) {
            throw new ApplicationException("Invalid Name: " + name);
        }
    }

    public IndexOptionData getIndexOptions(String name, String expiry, String optionType, String strikePrice) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl("https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxFOGetQuoteJSON.jsp").queryParam("underlying", new Object[]{name}).queryParam("expiry", new Object[]{expiry}).queryParam("type", new Object[]{optionType}).queryParam("strike", new Object[]{strikePrice}).queryParam("instrument", new Object[]{"OPTIDX"}).build();
        String data = (String) RestFetcher.restClient.getForEntity(builder.toUri(), String.class).getBody();

        try {
            return (IndexOptionData) this.objectMapper.readValue(data, IndexOptionData.class);
        } catch (JsonProcessingException var8) {
            throw new ApplicationException("Data Fetch Failed");
        }
    }

    public HashMap<String, Object> evaluateIndicesData() {
        Calendar calendar = Calendar.getInstance();
        String currentTime = DateFormattingUtility.process(calendar, "dd-MM-yyyy HH:mm:ss");
        calendar.add(5, (5 - calendar.get(7) + 7) % 7);
        String expiry = DateFormattingUtility.process(calendar, "ddMMMyyyy").toUpperCase();
        float niftyPrice = Float.parseFloat(this.getIndice("NIFTY 50").getLastPrice().replaceAll(",", ""));
        long referenceNiftyPrice = (long) (niftyPrice - niftyPrice % 50.0F);
        final List<String> ceValues = new ArrayList();
        final List<String> peValues = new ArrayList();
        final AtomicReference<Float> totalCEChangeInInterest = new AtomicReference(0.0F);
        final AtomicReference<Float> totalPEChangeInInterest = new AtomicReference(0.0F);
        LongStream.range(-3L, 3L).boxed().map((i) -> {
            long var10000 = referenceNiftyPrice - i * 50L;
            return var10000 + ".00";
        }).forEach((strikePrice) -> {
            IndexOption indexOption = (IndexOption) this.getIndexOptions("NIFTY", expiry, "CE", strikePrice).getData().get(0);
            float ceValue = 0.0F;

            try {
                ceValue = Float.parseFloat(indexOption.getChangeinOpenInterest().replaceAll(",", ""));
            } catch (NumberFormatException var14) {
            }

            ceValues.add(String.join(",", currentTime, expiry, "CE", strikePrice, indexOption.getChangeinOpenInterest().replaceAll(",", ""), indexOption.getLastPrice()));
            indexOption = (IndexOption) this.getIndexOptions("NIFTY", expiry, "PE", strikePrice).getData().get(0);
            float peValue = 0.0F;

            try {
                peValue = Float.parseFloat(indexOption.getChangeinOpenInterest().replaceAll(",", ""));
            } catch (NumberFormatException var13) {
            }

            peValues.add(String.join(",", currentTime, expiry, "PE", strikePrice, indexOption.getChangeinOpenInterest().replaceAll(",", ""), indexOption.getLastPrice()));
            float finalCeValue = ceValue;
            totalCEChangeInInterest.updateAndGet((v) -> {
                return v + finalCeValue;
            });
            float finalPeValue = peValue;
            totalPEChangeInInterest.updateAndGet((v) -> {
                return v + finalPeValue;
            });
        });
        return new HashMap<String, Object>() {
            {
                this.put("CE Values", ceValues);
                this.put("PE Values", peValues);
                this.put("CE Change In Open Interest", totalCEChangeInInterest.get());
                this.put("PE Change In Open Interest", totalPEChangeInInterest.get());
                this.put("Is PE > CE", (Float) totalCEChangeInInterest.get() < (Float) totalPEChangeInInterest.get());
            }
        };
    }
}

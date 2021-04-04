package com.kevzz.stockcalc.nse.service.indices.derivative;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevzz.stockcalc.nse.fetch.RestFetcher;
import com.kevzz.stockcalc.nse.model.IndexDerivative;
import com.kevzz.stockcalc.nse.model.IndiceData;
import com.kevzz.stockcalc.nse.model.Stock;
import com.kevzz.stockcalc.util.DateFormattingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public IndiceData getIndice(String indiceName) {

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(
                "https://www.nseindia.com/api/chart-databyindex"
        )
                .queryParam("index", indiceName)
                .build();

        return RestFetcher.nseGetRequest(
                builder.toString(),
                IndiceData.class
        );

    }

    @Override
    public IndexDerivative getIndiceDerivateData(String indiceName) {

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(
                "https://www.nseindia.com/api/quote-derivative"
        )
                .queryParam("symbol", indiceName)
                .build();

        return RestFetcher.nseGetRequest(
                builder.toString(),
                IndexDerivative.class
        );

    }

    @Override
    public HashMap<String, Object> evaluateIndicesData() {

        IndexDerivative indexDerivative = getIndiceDerivateData("NIFTY");

        Calendar calendar = Calendar.getInstance();

        String currentTime = DateFormattingUtility.process(calendar, "dd-MM-yyyy HH:mm:ss");

        calendar.add(
                Calendar.DAY_OF_MONTH,
                (5 - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7
        );

        String expiry = DateFormattingUtility.process(calendar, "dd-MMM-yyyy");

        IndiceData niftyIndiceData = getIndice("NIFTY 50");

        IndiceData niftyBankIndiceData = getIndice("NIFTY BANK");


        float niftyPrice = Float.parseFloat(
                niftyIndiceData.getGrapthData()
                        .get(niftyIndiceData.getGrapthData().size() - 1).get(1)
        );

        float niftyBankPrice = Float.parseFloat(
                niftyBankIndiceData.getGrapthData()
                        .get(niftyBankIndiceData.getGrapthData().size() - 1).get(1)
        );


        long referenceNiftyPrice = (long) (niftyPrice - niftyPrice % 50);

        List<String> ceValues = new ArrayList<>();

        List<String> peValues = new ArrayList<>();

        AtomicReference<Float> totalCEChangeInInterest = new AtomicReference<>((float) 0);

        AtomicReference<Float> totalPEChangeInInterest = new AtomicReference<>((float) 0);

        LongStream.range(-3, 3).boxed().map(i -> String.valueOf(referenceNiftyPrice - i * 50)).forEach(strikePrice -> {

            Map<String, Stock> val = indexDerivative.getStocks().stream().filter(stock -> {
                return stock.getMetadata().getExpiryDate().equals(expiry) &&
                        stock.getMetadata().getStrikePrice().equals(strikePrice) &&
                        (
                                stock.getMetadata().getOptionType().equals("Call") ||
                                        stock.getMetadata().getOptionType().equals("Put")
                        );
            }).collect(Collectors.toMap(stock -> stock.getMetadata().getOptionType(), value -> value));


            try {

                Stock stock = val.get("Call");

                float ceValue = Float.parseFloat(stock.getMetadata().getChange());

                ceValues.add(
                        String.join(
                                ",",
                                currentTime,
                                expiry,
                                "CE",
                                strikePrice,
                                stock.getMetadata().getChange(),
                                stock.getMetadata().getLastPrice()
                        )
                );

                totalCEChangeInInterest.updateAndGet(v -> (float) (v + ceValue));

            } catch (NumberFormatException ex) {

            }

            try {

                Stock stock = val.get("Put");

                float peValue = Float.parseFloat(stock.getMetadata().getChange());

                peValues.add(
                        String.join(
                                ",",
                                currentTime,
                                expiry,
                                "PE",
                                strikePrice,
                                stock.getMetadata().getChange(),
                                stock.getMetadata().getLastPrice()
                        )
                );

                totalPEChangeInInterest.updateAndGet(v -> (float) (v + peValue));

            } catch (NumberFormatException ex) {

            }

        });

        return new HashMap<String, Object>() {{
            put("CE Values", ceValues);
            put("PE Values", peValues);
            put("CE Change In Open Interest", totalCEChangeInInterest.get());
            put("PE Change In Open Interest", totalPEChangeInInterest.get());
            put("Is PE > CE", totalCEChangeInInterest.get() < totalPEChangeInInterest.get());
        }};

    }
}

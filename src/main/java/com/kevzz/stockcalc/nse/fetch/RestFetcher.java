package com.kevzz.stockcalc.nse.fetch;

import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RestFetcher<T> {

    public static final RestTemplate restClient = new RestTemplateBuilder().requestFactory(
            (Supplier) () -> new HttpComponentsClientHttpRequestFactory(
                    HttpClients.createDefault()
            )
    )
            .interceptors(new CustomClientHttpRequestInterceptorWithData()).build();

    public static <T> T nseGetRequest(String url, Class<T> returnType) {

        try {

            return RestFetcher.restClient.getForObject(url, returnType);

        } catch (HttpClientErrorException exception) {

            HttpEntity<String> entity = RestFetcher.restClient.getForEntity("https://www.nseindia.com", String.class);

            Map<String, String> headers = new HashMap<>();

            headers.put("cookie", entity.getHeaders().get("set-cookie").stream()
                    .map(
                            header -> header.split(";")[0]
                    ).collect(Collectors.joining(";")));

            ((CustomClientHttpRequestInterceptorWithData) RestFetcher.restClient.getInterceptors().get(0))
                    .setAdditionalHeaders(headers);

            return RestFetcher.restClient.getForObject(url, returnType);
        }
    }

}

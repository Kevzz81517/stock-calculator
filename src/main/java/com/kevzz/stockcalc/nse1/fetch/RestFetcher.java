package com.kevzz.stockcalc.nse1.fetch;

import java.io.IOException;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestFetcher {
    public static final RestTemplate restClient = (new RestTemplateBuilder()).requestFactory(() -> {
        return new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    }).interceptors(new ClientHttpRequestInterceptor[]{new ClientHttpRequestInterceptor() {
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add("Accept", "*/*");
            headers.add("Accept-Encoding", "gzip, deflate, br");
            headers.add("Accept-Language", "en-US,en;q=0.9");
            headers.add("Connection", "keep-alive");
            headers.add("Host", "www1.nseindia.com");
            headers.add("Referer", "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp");
            headers.add("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
            headers.add("sec-ch-ua-mobile", "?0");
            headers.add("Sec-Fetch-Dest", "empty");
            headers.add("Sec-Fetch-Mode", "cors");
            headers.add("Sec-Fetch-Site", "same-origin");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
            headers.add("X-Requested-With", "XMLHttpRequest");
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }}).build();

    public RestFetcher() {
    }
}

package com.kevzz.stockcalc.nse.fetch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class CustomClientHttpRequestInterceptorWithData implements ClientHttpRequestInterceptor {

    private Map<String, String> additionalHeaders = new HashMap<>();


    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = httpRequest.getHeaders();

        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "en-US,en;q=0.9");
        headers.add("Connection", "keep-alive");
        headers.add("Host", "www.nseindia.com");
        headers.add("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("referer", "https://www.nseindia.com/get-quotes/derivatives?symbol=FINNIFTY");
        headers.add("Sec-Fetch-Dest", "empty");
        headers.add("Sec-Fetch-Mode", "cors");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        headers.add("X-Requested-With", "XMLHttpRequest");
        this.additionalHeaders.keySet().forEach(key -> headers.add(key, this.additionalHeaders.get(key)));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

}

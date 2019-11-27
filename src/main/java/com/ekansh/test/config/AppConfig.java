package com.ekansh.test.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Configuration
@Component
public class AppConfig implements WebMvcConfigurer {

    @Value("${httpClient.connection.pool.size:100}")
    private String poolMaxTotal;

    @Value("${httpClientFactory.connection.timeout:90000}")
    private String connectionTimeOut;

    @Value("${httpClientFactory.read.timeout}")
    private String readTimeOut;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplate(Integer.parseInt(connectionTimeOut), Integer.parseInt(readTimeOut), Integer.parseInt(poolMaxTotal));
    }

    public RestTemplate restTemplate(int connectionTimeout, int readTimeout, int maxConnections) {
        RestTemplate template = new RestTemplate(httpRequestFactory(connectionTimeout, readTimeout, maxConnections));
        List<HttpMessageConverter<?>> messageConverters = template.getMessageConverters();
        messageConverters.add(new FormHttpMessageConverter());
        template.setMessageConverters(messageConverters);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setWriteAcceptCharset(true);
        for (int i = 0; i < template.getMessageConverters().size(); i++) {
            if (template.getMessageConverters().get(i) instanceof StringHttpMessageConverter) {
                template.getMessageConverters().remove(i);
                template.getMessageConverters().add(i, stringHttpMessageConverter);
                break;
            }
        }

        return template;
    }

    public HttpClient httpClient(int noOfConnections) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(noOfConnections);
        return HttpClientBuilder.create().setConnectionManager(connectionManager).build();
    }

    public ClientHttpRequestFactory httpRequestFactory(int connectionTimeout, int readTimeout, int maxConnections) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient(maxConnections));
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

}

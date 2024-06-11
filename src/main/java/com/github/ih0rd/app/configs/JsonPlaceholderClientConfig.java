package com.github.ih0rd.app.configs;

import com.github.ih0rd.app.clients.JsonPlaceholderApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class JsonPlaceholderClientConfig {

    @Value("${json-placeholder.url}")
    private String apiUrl;

    @Bean
    public JsonPlaceholderApiClient jsonPlaceholderApiClient() {
        var webClient = WebClient.builder().baseUrl(apiUrl).build();
        var adapter = WebClientAdapter.create(webClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(JsonPlaceholderApiClient.class);
    }
}

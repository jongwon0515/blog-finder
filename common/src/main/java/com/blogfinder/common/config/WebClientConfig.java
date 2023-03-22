package com.blogfinder.common.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {

	private HttpClient httpClient() {
		return HttpClient.create()
				.tcpConfiguration(client ->
				client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 70000))
				.tcpConfiguration(client ->
				client.doOnConnected(conn -> conn
						.addHandlerLast(new ReadTimeoutHandler(60))
						.addHandlerLast(new WriteTimeoutHandler(60))));
	}

	@Bean
	public WebClient restCustomClient() {
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient()))
				.defaultHeaders(httpHeaders -> {
					httpHeaders.setContentType(MediaType.APPLICATION_JSON);
					httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
				})
				.filter(requestLog())
				.build();
	}

	@Bean
	public WebClient formCustomClient() {

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient()))
				.defaultHeaders(httpHeaders ->
					httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
				)
				.filter(requestLog())
				.build();
	}

	private static ExchangeFilterFunction requestLog() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("Header: {}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}
}
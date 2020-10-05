package com.nordea.countries;

import io.netty.channel.ChannelOption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class CountriesApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public WebClient getWebClient() {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client ->
						client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000));

		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(CountriesApplication.class, args);
	}

}

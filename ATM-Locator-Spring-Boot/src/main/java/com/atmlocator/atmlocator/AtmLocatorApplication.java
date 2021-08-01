package com.atmlocator.atmlocator;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
public class AtmLocatorApplication {

	@RefreshScope
	public static void main(String[] args) {
		SpringApplication.run(AtmLocatorApplication.class, args);
	}


	@Bean
	@Profile("default")
	@RefreshScope
	public RestTemplate getRestTemplateForDefault() {
		RestTemplate restTemplate =new RestTemplate();
		return restTemplate;
	}

	@Bean
	@Profile("security")
	@RefreshScope
	public RestTemplate getRestTemplateForSecurity() throws Exception {
		TrustStrategy acceptingTrustStrategy;
		acceptingTrustStrategy = new TrustStrategy() {
			@Override
			public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
				.loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(csf)
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;
	}

}

package com.neotechlabs.justgifit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.annotation.PostConstruct;
import java.io.File;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class,
		JmxAutoConfiguration.class, WebSocketAutoConfiguration.class})
public class JustGifItApplication {

	@Value("${spring.http.multipart.location}/gif/")
	private String gifLoacation;

	public static void main(String[] args) {
		SpringApplication.run(JustGifItApplication.class, args);
	}

	@PostConstruct
	private void init() {
		File gifFolder = new File(gifLoacation);
		if (!gifFolder.exists()) {
			gifFolder.mkdir();
		}
	}

	@Bean
	public FilterRegistrationBean deRegisterHiddenHttpMethodFilter(
			HiddenHttpMethodFilter filter) {
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.setEnabled(false);
		return bean;
	}

	@Bean
	public FilterRegistrationBean deRegisterHttpPutFormContentFilter(
			HttpPutFormContentFilter filter) {
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.setEnabled(false);
		return bean;
	}

	@Bean
	public FilterRegistrationBean deRegisterRequestContextFilter(
			RequestContextFilter filter) {
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.setEnabled(false);
		return bean;
	}
}

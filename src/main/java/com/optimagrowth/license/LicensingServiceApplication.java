package com.optimagrowth.license;

import com.optimagrowth.license.model.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Sink.class)
@Slf4j
public class LicensingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}

	@StreamListener(Sink.INPUT)
	public void loggerSink(OrganizationChangeModel orgChange) {
		log.info("Received an {} event for organization id {}", orgChange.getAction(), orgChange.getId());
	}
}

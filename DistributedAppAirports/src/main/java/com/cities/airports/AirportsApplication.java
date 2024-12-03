package com.cities.airports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RefreshScope
public class AirportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportsApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale rus = new Locale("ru", "RU");
		localeResolver.setDefaultLocale(rus);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public InfoContributor getInfoContributor() {
		Map<String, Object> details = new HashMap<>();
		details.put("assemblyInformation", "0.1v");
		details.put("nameApp", "Airports");
		details.put("description", "Airports");
		details.put("developers", "Lityagin Semen, Mikhailova Oksana");
		details.put("e-mail", "semen@gmail.com, oksana@mail.ru");
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("info", details);
		return new MapInfoContributor(wrapper);
	}
}


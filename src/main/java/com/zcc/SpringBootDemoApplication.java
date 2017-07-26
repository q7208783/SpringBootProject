package com.zcc;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.zcc.util.PropertyAccessorUtil;

@SpringBootApplication
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}


	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		//设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}

	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource
			=new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}
}

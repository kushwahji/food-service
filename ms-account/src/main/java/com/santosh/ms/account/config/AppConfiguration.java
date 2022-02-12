package com.santosh.ms.account.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.santosh.ms.account.logs.LoggerIntercepter;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @Auther Santosh-Kus Date: 12-12-2021
 */
@Configuration
@EnableWebMvc
public class AppConfiguration implements WebMvcConfigurer {

	@Value("${source.group-name}")
	private String groupName;


	@Value("${source.title}")
	private String title;

	@Value("${source.description}")
	private String desc;

	@Value("${source.version}")
	private String version;

	@Value("${source.licence-name}")
	private String licenceName;

	@Value("${source.licence-url}")
	private String licenceUrl;

	@Value("${source.app-url}")
	private String appUrl;
	@Autowired
	private LoggerIntercepter logInterceptor;

	@Bean
	public GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder().group(groupName).pathsToMatch("/**").build();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title(title).version(version).license(new License().name(licenceName).url(licenceUrl)))
				.externalDocs(new ExternalDocumentation().description(desc).url(appUrl));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(logInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}

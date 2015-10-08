package com.marcoslozina.testejava.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.marcoslozina.testejava.converter.LivroConverter;
import com.marcoslozina.testejava.converter.PessoaConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.marcoslozina.testejava")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    LivroConverter livroConverter;

    @Autowired
    PessoaConverter pessoaConverter;

    @Bean
    public ViewResolver viewResolver() {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setViewClass(JstlView.class);
	viewResolver.setPrefix("/WEB-INF/views/");
	viewResolver.setSuffix(".jsp");

	return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("messages");
	return messageSource;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
	registry.addConverter(livroConverter);
	registry.addConverter(pessoaConverter);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
	matcher.setUseRegisteredSuffixPatternMatch(true);
    }

}

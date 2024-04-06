/*
 * SpringMockMvcConfiguration.java
 *
 * Created on 2018-05-14, 7:02
 */
package com.marcnuri.demo.springmockmvc;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-05-14.
 */
@Configuration
@EnableWebMvc
@EnableConfigurationProperties(ServerProperties.class)
@ComponentScan(value = "com.marcnuri.demo.springmockmvc",
    excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".*Test.*"))
public class SpringMockMvcConfiguration implements WebMvcConfigurer {

//**************************************************************************************************
//  Fields
//**************************************************************************************************

//**************************************************************************************************
//  Constructors
//**************************************************************************************************

//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************

//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/index.html").addResourceLocations("/index.html");
  }

//**************************************************************************************************
//  Other Methods
//**************************************************************************************************
  /**
   * Resolves any .html file as a view (used in 'forward:' controllers)
   *
   * @return InternalResourceViewResolver with the specified configuration
   */
  @Bean
  public InternalResourceViewResolver htmlViewResolver() {
    InternalResourceViewResolver resolver= new InternalResourceViewResolver();
    resolver.setPrefix("/");
    resolver.setSuffix(".html");
    return resolver;
  }

  @Bean
  @RequestScope
  public User generateUser(){
    return new User(-1);
  }

//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************

//**************************************************************************************************
//  Static Methods
//**************************************************************************************************

//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************

}

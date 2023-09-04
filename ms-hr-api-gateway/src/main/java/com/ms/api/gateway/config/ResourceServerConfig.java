package com.ms.api.gateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" };

	private static final String[] OPERATOR = { "/hr-worker/**" };

	private static final String[] ADMIN = { "/hr-payroll/**", "/hr-user/**","/actuator/**","/hr-worker//actuator/**","/hr-oauth/actuator/**" };

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> requests.antMatchers(PUBLIC).permitAll()
				.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN").antMatchers(ADMIN)
				.hasRole("ADMIN").anyRequest().authenticated());
		
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource () {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","PATCH"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}
	
	@Bean
	FilterRegistrationBean<CorsFilter> corFilter () {
		FilterRegistrationBean<CorsFilter> filterRegistrationBean
				= new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource ()));
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);		
		return filterRegistrationBean;
		
	}

}

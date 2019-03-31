package br.com.crud.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import crud.crud.security.FilterOrigin;

/**
 * 
 * @author Rafael Castro
 * Classe com as configurações de liberações de PATHS, para uso do spring security
 *@version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    
    /**
     * Liberação de paths para o swagger
     * @see br.com.crud.config.SwaggerConfig.informacoesApi()
     */
    private static final String[] PUBLIC_MATCHERS_SWAGGER = {
			"/swagger-ui.html/**",
			"/swagger-resource/**",
			"/**",
			"/css/**",
			"/resources/**",
			"/webjars/**",
			"/swagger-resources/**"
	};
    
    private static final String[] PUBLIC_MATCHERS = {
    		"/h2-console/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = {
    		"/clientes/**",
    		"/clientes/forgot"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS_SWAGGER).permitAll()
                .antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();
        	http.addFilter(new FilterOrigin(authenticationManager()));
    }
    
    @Bean
	  CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues(); 
		configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS", "PATCH"));
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**",configuration);
	    return source;
	  }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

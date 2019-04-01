package crud.crud.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class FilterOrigin extends BasicAuthenticationFilter  {

	private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");

	public FilterOrigin(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	 @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain chain) throws IOException, ServletException {

	        String origin = request.getHeader("Origin");

	        response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200" );
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        chain.doFilter(request, response);
	    }



}
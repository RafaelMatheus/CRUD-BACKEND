package br.com.crud.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping(value = "/")
public class SwaggerController {
	@RequestMapping(method=RequestMethod.GET)
	public RedirectView  redirecionarPagSwagger() {
		return new RedirectView("/swagger-ui.html");
	}
}

package br.com.crud.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class SwaggerController {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView  redirecionarPagSwagger() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}
}

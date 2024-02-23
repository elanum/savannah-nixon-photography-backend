package de.savannahnixon.backend.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Configuration
public class OpenApiDefinition {
  @GetMapping("/openapi.json")
  public ModelAndView forward() {
    return new ModelAndView("forward:/openapi");
  }

  public static final String SHOOTING_CATEGORIES = "Shooting Categories";
  public static final String SHOOTING_PACKAGES = "Shooting Packages";
}

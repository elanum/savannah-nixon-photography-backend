package de.savannahnixon.backend.app.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Controller
@Configuration
public class OpenApiDefinition {
  @Value("${pom.version}")
  private String version;

  @Value("${backend.url}")
  private String backendUrl;
  // @Value("${spring.application.version}")
  // private String appVersion;

  @GetMapping("/openapi.json")
  public ModelAndView forward() {
    return new ModelAndView("forward:/openapi");
  }

  public static final String CATEGORIES = "Categories";
  public static final String SHOOTINGS = "Shootings";
  public static final String IMAGES = "Images";

  @Bean
  public OpenAPI generateOpenAPISpecification() {
    final Contact contact = new Contact().name("Manuel Schierenberg").email("mail@elanum.de").url("https://elanum.de/");

    final License license = new License().name("MIT")
        .url("https://github.com/elanum/savannah-nixon-photography-backend/blob/main/LICENSE");

    final Info info = new Info().title("Savannah Nixon Photography API").version(version).contact(contact).description(
        "This is the API for the Savannah Nixon Photography application. It provides access to the shooting categories, shooting packages, and images.")
        .license(license);

    final List<Server> servers = new ArrayList<>();
    servers.add(new Server().url(backendUrl).description("Default server url"));

    return new OpenAPI().info(info).servers(servers);
  }
}

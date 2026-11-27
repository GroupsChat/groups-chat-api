package com.groups.chat.groups_chat.routes;

import com.groups.chat.groups_chat.controllers.HealthController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Routes implements WebMvcConfigurer {

  private final HealthController healthController;

  public Routes(HealthController healthController) {
    this.healthController = healthController;
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    // Configurações globais de rotas, se necessário.
  }
}

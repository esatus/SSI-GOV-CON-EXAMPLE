package com.esatus.ssi.gov.connector.example.controller;

import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.esatus.ssi.shared.dbmodel.SeLFComponent;
import lombok.extern.slf4j.Slf4j;

@Controller
@Scope("singleton")
@Slf4j
public class StartupController {


  @Autowired
  public StartupController(@Qualifier("SSI-GOV-CORE") RestTemplate ssiGovCoreRestTemplate,
      @Value("${ssi.version:1.2}") String version) {

    try {
      Properties gitProperties = new Properties();
      try {
        gitProperties.load(StartupController.class.getClassLoader().getResourceAsStream("git.properties"));
      } catch (IOException exc) {
        log.error("Failed to load GIT properties", exc);
      }

      SeLFComponent component = new SeLFComponent();
      component.name = "SSI-GOV-CON-EXAMPLE";
      component.version = version;
      component.build = gitProperties.getProperty("git.commit.id.abbrev", "n/a");
      component.buildTime = gitProperties.getProperty("git.build.time", "n/a");

      if (ssiGovCoreRestTemplate.postForObject("/api/info/register", component, Boolean.class)) {
        log.info("Component registered successfully");
      }
    } catch (Exception exc) {
      log.error("Failed to register component", exc);
    }
  }
}

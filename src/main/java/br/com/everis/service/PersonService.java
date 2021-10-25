package br.com.everis.service;

import br.com.everis.controller.PersonController;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    //@Value("${person.service.greeting}")
    @Property(name = "person.service.greeting", defaultValue = "oi")
    private String greeting;

    @EventListener
    public void onStartup(StartupEvent startupEvent) {
        LOG.debug("Startup: {} " + startupEvent.toString(), PersonService.class.getSimpleName());
    }

    public String sayHi() {
        return greeting;
    }
}

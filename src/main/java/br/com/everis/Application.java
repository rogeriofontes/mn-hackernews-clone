package br.com.everis;

import br.com.everis.service.PersonService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OpenAPIDefinition(
    info = @Info(
            title = "mn-hackernews-clone",
            version = "0.1",
            description = "Api de Clone do ",
            license = @License(name = "MIT")
    )
)
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        //Micronaut.run(Application.class, args);
        ApplicationContext cxt = Micronaut.run(Application.class, args);
        PersonService bean = cxt.getBean(PersonService.class);
        LOG.info(bean.sayHi());
    }
}

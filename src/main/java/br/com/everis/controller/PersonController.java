package br.com.everis.controller;

import br.com.everis.controller.http.Message;
import br.com.everis.model.domain.Person;
import br.com.everis.repository.PersonRepository;
import br.com.everis.service.PersonService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
//@Controller("/persons")
@Controller("${person.controller.path:/persons}")
//@Operation(summary = "Creates a new bar object adding a decorated id and the current time",description = "Showcase of the creation of a dto")
@Secured({SecurityRule.IS_AUTHENTICATED})
public class PersonController {

    /*

@ApiResponse(responseCode = "201", description = "Bar object correctly created",content = @Content(mediaType = "application/json",schema = @Schema(type="BarDto")))
@ApiResponse(responseCode = "400", description = "Invalid id Supplied")
@ApiResponse(responseCode = "500", description = "Remote error, server is going nuts")    @Tag(name = "create")
     */

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Inject
    private PersonService personService;

    @Inject
    private PersonRepository personRepository;

    @Post()
    @Operation(summary = "Create entities", description = "Create a list of entities", tags = "Entity",
            responses = {
                    @ApiResponse(description = "A list of Entity responses for the created entities", responseCode = "201", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                    @ApiResponse(description = "Entity not found", responseCode = "404")}
    )
    public HttpResponse<?> savePerson(@Body @Valid Person person) {
        this.personRepository.save(person);
        return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(), "Saved successfully !"));
    }

    @Get("/hi")
    @Operation(summary = "Get users",
            description = "Get list of users")
    public HttpResponse<?> getHi() {
        LOG.info("Sau HI");
        return HttpResponse.status(HttpStatus.OK).body(this.personService.sayHi());
    }

    @Version("1")
    @Get("{?max,offset}")
    @Operation(summary = "Get all entities", description = "Gets a list of all the entities", tags = "Entity",
            responses = {
                    @ApiResponse(description = "A list of entities", responseCode = "200", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                    @ApiResponse(description = "Entity not found", responseCode = "404")
            })
    public List<Person> findAll(@Nullable Integer max, @Nullable Integer offset) {
        return getPeople().stream()
                .skip(offset == null ? 0 : offset)
                .limit(max == null ? 10000 : max)
                .collect(Collectors.toList());
    }

    @Version("2")
    @Get("?max,offset")
    @Operation(summary = "Get all entities", description = "Gets a list of all the entities", tags = "Entity",
            responses = {
                    @ApiResponse(description = "A list of entities", responseCode = "200", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                    @ApiResponse(description = "Entity not found", responseCode = "404")
            })
    public List<Person> findAllV2(@NotNull Integer max, @NotNull Integer offset) {
        return getPeople().stream()
                .skip(offset == null ? 0 : offset)
                .limit(max == null ? 10000 : max)
                .collect(Collectors.toList());
    }

    private List<Person> getPeople() {
        Iterable<Person> all = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        all.iterator().forEachRemaining(persons::add);
        return persons;
    }

    /*@Get()
    public HttpResponse<?> getPersons() {
        return HttpResponse.status(HttpStatus.OK).body(this.personRepository.findAll());
    }*/


    @Get("/{id}")
    @Operation(summary = "Save/Update Entity", description = "From the given id, creates an entity with specified id, " +
            "otherwise it overwrites the existing entity with new information",tags = "Entity",
            responses = {
                    @ApiResponse(description = "Response status indicating if entity was created", responseCode = "200", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                    @ApiResponse(description = "Entity not found", responseCode = "404")}
    )
    public Optional<Person> findById(Long id) {
        Optional<Person> personById = personRepository.findById(id);
        return personById.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Get("/peaple")
    public Single<List<Person>> getPersons() {
        return Single.just(getPeople());
    }

}




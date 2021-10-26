package br.com.everis.controller;

import br.com.everis.dtos.UserDto;
import br.com.everis.model.domain.Person;
import br.com.everis.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller("/signup")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @Post
    @Operation(summary = "Create entities", description = "Create a list of entities", tags = "Entity",
            responses = {
                    @ApiResponse(description = "A list of Entity responses for the created entities", responseCode = "201", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                    @ApiResponse(description = "Entity not found", responseCode = "404")}
    )
    public Single<HttpResponse<UserDto>> registerUser(UserDto userDto) {

        Optional<UserDto> existingUser = userService.findUser(userDto.getUsername());

        UserDto user = userService.createUser(userDto);
        return Single.just(existingUser.map(HttpResponse::badRequest)
                .orElse(HttpResponse.ok(user)));
    }
}

package br.com.everis.auth;

import br.com.everis.dtos.UserDto;
import br.com.everis.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class BasicAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @SneakyThrows
    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        log.info("Usuario: {}  e senha {} da Autenticacao", username, password);

        Optional<UserDto> existingUser = userService.findUser(username); //.orElseThrow(() -> new Exception());
        if (existingUser.isEmpty()) throw new Exception("Erro ao buscar usario");

        return Flux.create(emmitter -> {
            if (username.equals(existingUser.get().getUsername()) &&
                    password.equals(existingUser.get().getPassword())) {
                emmitter.next(AuthenticationResponse.success(username));
                emmitter.complete();
            } else {
                emmitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}

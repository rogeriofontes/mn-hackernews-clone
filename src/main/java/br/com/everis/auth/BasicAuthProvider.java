package br.com.everis.auth;

import br.com.everis.dtos.UserDto;
import br.com.everis.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class BasicAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        Optional<UserDto> existingUser = userService.findUser(username);

        return Flux.create(emmitter -> {
            if (authenticationRequest.getIdentity().equals(existingUser.get().getUsername()) &&
                    authenticationRequest.getSecret().equals(existingUser.get().getPassword())) {
                emmitter.next(AuthenticationResponse.success(username));
                emmitter.complete();
            } else {
              emmitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}

package br.com.everis.auth;

import br.com.everis.dtos.UserDto;
import br.com.everis.service.UserService;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.errors.OauthErrorResponseException;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Optional;

import static io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT;

@Singleton
@RequiredArgsConstructor
public class RefreshTokenHandler implements RefreshTokenPersistence {
    private final UserService userService;

    @Override
    @EventListener
    public void persistToken(RefreshTokenGeneratedEvent event) {
        userService.saveRefreshToken(event.getAuthentication().getName(), event.getRefreshToken());
    }

    @Override
    public Publisher<Authentication> getAuthentication(String refreshToken) {
        return Flux.create(emitter -> {
            Optional<UserDto> tokenOpt = userService.findByRefreshToken(refreshToken);
            if (tokenOpt.isPresent()) {
                UserDto token = tokenOpt.get();
                if (token.getToken() == null) {
                    emitter.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null));
                } else {
                    emitter.next(Authentication.build(token.getUsername()));
                    emitter.complete();
                }
            } else {
                emitter.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null));
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}

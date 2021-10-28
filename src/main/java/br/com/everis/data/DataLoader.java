/*package br.com.everis.data;

import br.com.everis.model.domain.User;
import br.com.everis.repository.UserRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final UserRepository usersRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        List<User> users = Arrays.asList(
                User.builder().username("user1").password("password1").build(),
                User.builder().username("user2").password("password2").build(),
                User.builder().username("user3").password("password3").build()
        );
        usersRepository.saveAll(users);

        log.info("Demo data added : "+users.size()+" users");
    }
}*/

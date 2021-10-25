package br.com.everis.mapper;

import br.com.everis.dtos.UserDto;
import br.com.everis.model.domain.User;
import jakarta.inject.Singleton;

@Singleton
public class UserMapper {

    public User toEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole()).build();
    }


    public UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole()).build();
    }
}

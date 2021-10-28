package br.com.everis.model.domain;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_user")
@Schema(name="User", description = "Represents an User Entity")
public class User {

    public static final String DEFAULT_ROLE = "VIEW";

    @Id
    @GeneratedValue
    @Schema(description = "The unique id for the entity", example = "1")
    private Long id;
    @Schema(description = "Username of the entity", maxLength = 20, required = true, example = "Username")
    private String username;
    @Schema(description = "Password of the entity", maxLength = 20, required = true, example = "Password")
    private String password;

    @Builder.Default
    @Schema(description = "Role of the entity", maxLength = 20, required = true, example = "Role")
    private String role = DEFAULT_ROLE;

    @Schema(description = "Token of the entity", maxLength = 20, required = true, example = "Token")
    private String token;
}

package br.com.everis.model.domain;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Introspected
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tb_person")
@Schema(name="Person", description = "Represents an Person Entity")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Schema(description = "The unique id for the entity", example = "1")
    private Long id;

    @NotEmpty(message = "can not be empty")
    @Size(min = 1, max = 20)
    @Getter
    @Setter
    @Schema(description = "Name of the entity", maxLength = 20, required = true, example = "EntityName")
    private String name;

    @Min(18)
    @PositiveOrZero
    @Getter
    @Setter
    @Schema(description = "age", minLength = 18, required = true, example = "21")
    private int age;

    // @NotNull
    //private Gender gender;

}

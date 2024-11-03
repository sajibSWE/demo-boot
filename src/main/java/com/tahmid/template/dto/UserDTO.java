package com.tahmid.template.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name must have at least 1 character")
    private String name;


    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be non-negative")
    private Integer age;

    @NotNull(message = "City cannot be null")
    private String city;

}

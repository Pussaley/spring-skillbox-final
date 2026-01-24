package com.example.hotelbooking.web.dto.user.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Поле username обязательно к заполнению")
    private String username;
    @NotBlank(message = "Поле password обязательно к заполнению")
    private String password;
    @NotBlank(message = "Поле email обязательно к заполнению")
    private String email;
}
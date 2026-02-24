package com.example.hotelbooking.web.dto.user.create;

import com.example.hotelbooking.domain.entity.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Email(flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotNull(message = "Роль должна быть указана")
    private RoleType role;
}
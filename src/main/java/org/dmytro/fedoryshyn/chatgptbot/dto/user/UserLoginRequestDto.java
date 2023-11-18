package org.dmytro.fedoryshyn.chatgptbot.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 5, max = 30)
    private String login;
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
}

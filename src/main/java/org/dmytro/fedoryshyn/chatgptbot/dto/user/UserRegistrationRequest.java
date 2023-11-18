package org.dmytro.fedoryshyn.chatgptbot.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dmytro.fedoryshyn.chatgptbot.validation.PasswordsMatch;

@Data
@PasswordsMatch
public class UserRegistrationRequest {
    private String login;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    private String repeatPassword;
    private String role;
}

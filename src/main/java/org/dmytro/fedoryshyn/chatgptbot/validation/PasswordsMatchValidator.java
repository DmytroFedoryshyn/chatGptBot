package org.dmytro.fedoryshyn.chatgptbot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserRegistrationRequest;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UserRegistrationRequest userRequestDto) {
            return Objects.equals(userRequestDto.getPassword(), userRequestDto.getRepeatPassword());
        }
        return false;
    }
}

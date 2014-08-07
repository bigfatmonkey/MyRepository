package net.savka.validator;

import net.savka.entities.User;

import java.util.Map;

public interface UserValidator extends Validator<User> {
    @Override
    public Map<String, String> validate(User entity);
}

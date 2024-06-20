package jsl.jayva.api.controller;

import jakarta.validation.Valid;
import jsl.jayva.api.UserApi;
import jsl.jayva.api.service.UserEntityService;
import jsl.jayva.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @Override
    public ResponseEntity<User> createUser(@Valid User user) {
        return ResponseEntity.ok(userEntityService.createUser(user));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String userId) {
        userEntityService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<User> getUserById(String userId) {
        return ResponseEntity.ok(userEntityService.getUserById(userId));
    }

    @Override
    public ResponseEntity<User> updateUserById(String userId, @Valid User user) {
        return ResponseEntity.ok(userEntityService.updateUserById(userId, user));
    }

    @Override
    public ResponseEntity<User> addUserAuthority(String userId, String authority) {
        return ResponseEntity.ok(userEntityService.addUserAuthority(userId, authority));
    }
}

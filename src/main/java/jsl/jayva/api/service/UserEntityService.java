package jsl.jayva.api.service;

import jsl.jayva.model.User;

public interface UserEntityService {
    User createUser(User user);
    void deleteUserById(String userId);
    User getUserById(String userId);
    User updateUserById(String userId, User user);
    User addUserAuthority(String userId, String authority);
}

package jsl.jayva.api.service.implementations;

import jsl.jayva.api.entities.AuthorityEntity;
import jsl.jayva.api.entities.UserEntity;
import jsl.jayva.api.repository.UserEntityRepository;
import jsl.jayva.api.service.UserEntityService;
import jsl.jayva.model.Authority;
import jsl.jayva.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImplementation implements UserEntityService {
    private final UserEntityRepository userEntityRepository;

    public UserEntityServiceImplementation(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public User createUser(User user) {
        return userEntityToUser(userEntityRepository.save(userToUserEntity(user)));
    }

    @Override
    public void deleteUserById(String userId) {
        userEntityRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
        var userEntity = userEntityRepository.findById(userId);
        return userEntity.map(this::userEntityToUser).orElse(null);
    }

    @Override
    public User updateUserById(String userId, User user) {
        var userEntity = userEntityRepository.findById(userId);
        if (userEntity.isPresent()) {
            var foundUser = userEntity.get().username(user.getUsername()).password(user.getPassword());
            return userEntityToUser(userEntityRepository.save(foundUser));
        }

        return null;
    }

    @Override
    public User addUserAuthority(String userId, String authority) {
        var role = new AuthorityEntity(authority);
        var user = userEntityRepository.findById(userId);
        if (user.isPresent()) {
            var foundUser = user.get();
            foundUser.addAuthority(role);
            return userEntityToUser(userEntityRepository.save(foundUser));
        }
        return null;
    }

    private User userEntityToUser(UserEntity userEntity) {
        var user = new User();
        var authority = userEntity.getAuthorities()
                .stream().map(authorityEntity -> {
                    var roles = new Authority();
                    return roles.id(authorityEntity.getId());
                }).toList();

        return user.id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(null)
                .authorities(
                        authority
                );
    }

    private UserEntity userToUserEntity(User user) {
        return UserEntity.init().username(user.getUsername()).password(user.getPassword()).enabled(1);
    }
}

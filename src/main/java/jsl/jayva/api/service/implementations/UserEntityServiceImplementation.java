package jsl.jayva.api.service.implementations;

import jsl.jayva.api.entities.AuthorityEntity;
import jsl.jayva.api.hateoas.UserEntityAssemblerSupport;
import jsl.jayva.api.repository.UserEntityRepository;
import jsl.jayva.api.service.UserEntityService;
import jsl.jayva.api.utility.UserEntityUtility;
import jsl.jayva.model.User;
import org.springframework.stereotype.Service;

import static jsl.jayva.api.utility.UserEntityUtility.userEntityToUser;
import static jsl.jayva.api.utility.UserEntityUtility.userToUserEntity;

@Service
public class UserEntityServiceImplementation implements UserEntityService {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityAssemblerSupport userEntityAssemblerSupport;

    public UserEntityServiceImplementation(UserEntityRepository userEntityRepository, UserEntityAssemblerSupport userEntityAssemblerSupport) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityAssemblerSupport = userEntityAssemblerSupport;
    }

    @Override
    public User createUser(User user) {
        return userEntityAssemblerSupport.toModel(userEntityRepository.save(userToUserEntity(user)));
    }

    @Override
    public void deleteUserById(String userId) {
        userEntityRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
        var userEntity = userEntityRepository.findById(userId);
        return userEntity.map(UserEntityUtility::userEntityToUser).orElse(null);
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

}

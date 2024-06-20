package jsl.jayva.api.utility;

import jsl.jayva.api.entities.UserEntity;
import jsl.jayva.model.Authority;
import jsl.jayva.model.User;

public class UserEntityUtility {
    public static User userEntityToUser(UserEntity userEntity) {
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

    public static UserEntity userToUserEntity(User user) {
        return UserEntity.init().username(user.getUsername()).password(user.getPassword()).enabled(1);
    }
}

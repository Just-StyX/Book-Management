package jsl.jayva.api.hateoas;

import jsl.jayva.api.controller.UserController;
import jsl.jayva.api.entities.UserEntity;
import jsl.jayva.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

import static jsl.jayva.api.utility.UserEntityUtility.userEntityToUser;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserEntityAssemblerSupport extends RepresentationModelAssemblerSupport<UserEntity, User> {
    public UserEntityAssemblerSupport() {
        super(UserController.class, User.class);
    }

    @Override
    public User toModel(UserEntity userEntity) {
        User user = userEntityToUser(userEntity);
        BeanUtils.copyProperties(userEntity, user);
        user.add(linkTo(methodOn(UserController.class).createUser(user)).withSelfRel());
        user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withRel("read"));
        user.add(linkTo(methodOn(UserController.class).deleteUserById(user.getId())).withRel("delete"));
        user.add(linkTo(methodOn(UserController.class).updateUserById(user.getId(), user)).withRel("update"));
        user.add(linkTo(methodOn(UserController.class).addUserAuthority(user.getId(), "read")).withRel("authority"));
        return user;
    }
}

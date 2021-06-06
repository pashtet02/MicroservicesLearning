package com.example.userservice.controller.assembler;

import com.example.userservice.controller.UserController;
import com.example.userservice.controller.model.UserModel;
import com.example.userservice.dto.UserDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);

        Link get = linkTo(methodOn(UserController.class).getUserWithCards(entity.getId())).withRel("get");
        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel("create");
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getEmail(), entity))
                .withRel("update");
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getEmail()))
                .withRel("delete");

        userModel.add(get, create, update, delete);

        return userModel;
    }

}

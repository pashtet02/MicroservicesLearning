package com.example.userservice.api;

import com.example.userservice.controller.model.UserModel;
import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.ResponseTemplateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "path", required = true, value = "page number"),
            @ApiImplicitParam(name = "sortParam", paramType = "path", required = true, value = "sorting parameter"),
            @ApiImplicitParam(name = "numberOfUsers", paramType = "path", required = true, value = "Number of users per request"),
    })
    @ApiOperation("Get sorted list of all users with pagination")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<UserModel> getAllUsers(@RequestParam(value = "page") Integer page,
                                @RequestParam("sortParam") String sortParam,
                                @RequestParam("numOfUsers") Integer numOfUsers);

    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User username"),
    })
    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{username}")
    UserModel getUser(@PathVariable String username);*/

    @GetMapping("/{id}")
    ResponseTemplateVO getUserWithCards(@PathVariable("id") Long userId);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Valid @RequestBody UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User username"),
    })
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{username}")
    UserModel updateUser(@PathVariable String username, @RequestBody UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User username"),
    })
    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{username}")
    ResponseEntity<Void> deleteUser(@PathVariable String username);

}
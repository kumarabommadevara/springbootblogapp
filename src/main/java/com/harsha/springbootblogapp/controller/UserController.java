package com.harsha.springbootblogapp.controller;


import com.harsha.springbootblogapp.payloads.SignupRequest;
import com.harsha.springbootblogapp.payloads.UserDto;
import com.harsha.springbootblogapp.payloads.UserResponse;
import com.harsha.springbootblogapp.service.UserService;
import com.harsha.springbootblogapp.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "user", description = "Operations for user Api")
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;


    @ApiOperation(value = "View a list of available users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public UserResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return userService.getAllUser(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "user registration", response = UserDto.class)
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody SignupRequest signupRequest) {


        return new ResponseEntity<>(userService.createUser(signupRequest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update a user with an ID", response = UserDto.class)
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer id) {

        return new ResponseEntity<>(userService.updateUser(userDto, id), HttpStatus.CREATED);
    }

    //    @GetMapping("/all")
//    public ResponseEntity<List<UserDto>> getAllUsers() {
//
//        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
//    }
    @ApiOperation(value = "Get a user with an ID", response = UserDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a user with an ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.FORBIDDEN);
    }

}

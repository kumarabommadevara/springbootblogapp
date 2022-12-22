package com.harsha.springbootblogapp.service;

import com.harsha.springbootblogapp.payloads.SignupRequest;
import com.harsha.springbootblogapp.payloads.UserDto;
import com.harsha.springbootblogapp.payloads.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(SignupRequest signupRequest);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto findUserById(Integer userId);

    List<UserDto> findAllUser();

    void deleteUser(Integer userId);

    UserResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

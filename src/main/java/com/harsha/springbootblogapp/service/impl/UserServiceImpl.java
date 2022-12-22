package com.harsha.springbootblogapp.service.impl;

import com.harsha.springbootblogapp.entity.ERole;
import com.harsha.springbootblogapp.entity.Role;
import com.harsha.springbootblogapp.entity.User;
import com.harsha.springbootblogapp.exception.ResourceNotFoundException;
import com.harsha.springbootblogapp.payloads.SignupRequest;
import com.harsha.springbootblogapp.payloads.UserDto;
import com.harsha.springbootblogapp.payloads.UserResponse;
import com.harsha.springbootblogapp.repository.RoleRepository;
import com.harsha.springbootblogapp.repository.UserRepository;
import com.harsha.springbootblogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    static ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    private static User convertToEntity(UserDto userDto) {

        return modelMapper.map(userDto, User.class);

    }

    private static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);

    }


    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {

            throw new RuntimeException("Email already in use");
        } else {

            Set<String> strRoles = signupRequest.getRoles();
            List<Role> roles = new ArrayList<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    if ("admin".equals(role)) {
                        Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    if ("user".equals(role)) {

                        Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                });
            }
            User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),
                    encoder.encode(signupRequest.getPassword()), signupRequest.getAbout());
            user.setAbout(signupRequest.getAbout());
            user.setRole(roles);

            final User save = this.userRepository.save(user);

            return convertToDto(save);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            User user = convertToEntity(userDto);
            user.setId(userId);
            User updatedUser = userRepository.save(user);
            return convertToDto(updatedUser);
        } else {
            throw new ResourceNotFoundException("User Not Found");
        }
    }

    @Override
    public UserDto findUserById(Integer userId) {

        Optional<User> byId = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id Not present" + userId)));
        return convertToDto(byId.get());

    }

    @Override
    public List<UserDto> findAllUser() {


        return userRepository.findAll().stream().map(UserServiceImpl::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {

        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("User Id is not present");
        }

    }

    @Override
    public UserResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> users = this.userRepository.findAll(pageable);
        List<User> usersList = users.getContent();
        List<UserDto> content = usersList.stream().map(UserServiceImpl::convertToDto).toList();
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());


        return userResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByName(username)
                ;
    }
}

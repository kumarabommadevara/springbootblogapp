package com.harsha.springbootblogapp.repository;

import com.harsha.springbootblogapp.entity.User;
import com.harsha.springbootblogapp.payloads.UserDto;
import org.apache.el.parser.BooleanNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);

}

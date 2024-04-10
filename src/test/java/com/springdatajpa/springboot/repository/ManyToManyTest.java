package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Role;
import com.springdatajpa.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveUser() {
        User user = User.builder()
                .email("an@gamil.com")
                .firstName("an")
                .lastName("Minh")
                .password("minhan2202")
                .roles(
                        Set.of(Role.builder().name("ADMIN").build(),
                                Role.builder().name("EMPLOYEE").build())
                ).build();

        userRepository.save(user);
    }

    @Test
    void deleteUser() {
        User user = userRepository.findById(2L).get();
        userRepository.delete(user);
    }

    @Test
    void deleteRole() {
        Role role = roleRepository.findById(2L).get();
        roleRepository.delete(role);
    }

    @Test
    void getRoleWithUsers() {
        Role role = roleRepository.findById(5L).get();
        System.out.println(role.getUsers());
    }
}

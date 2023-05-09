package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png ");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleName("NORMAL");
        role.setRoleId(45L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);

        User user1 = this.userService.createUser(user, roles);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = this.userService.getUser(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getllUsers(){
        List<User> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

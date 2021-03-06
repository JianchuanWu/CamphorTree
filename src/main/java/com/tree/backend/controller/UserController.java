
package com.tree.backend.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tree.backend.model.User;
import com.tree.backend.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {

        user.setHeadImage(PhotoController.imageName);
        return userService.save(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String registerUser(@RequestBody Map<String, String> json) throws ServletException {

        if (json.get("userName") == null || json.get("password") == null) {
            throw new ServletException("Please fill in userName and password!");
        }
        String userName = json.get("userName");
        String password = json.get("password");
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new ServletException("User name not found.");
        }
        String pwd = user.getPassword();
        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check you name and password.");
        }
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }
}

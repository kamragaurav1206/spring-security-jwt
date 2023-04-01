package com.yash.controller;

import com.yash.entity.AuthRequest;
import com.yash.service.CustomUserDetailService;
import com.yash.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class WelcomeController {

    Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        System.out.println("Helloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        return new ResponseEntity<>("Welcome!!", HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        logger.info("user trying to autheticate "+authRequest.getUserName());
        System.out.println("user trying to autheticate " + authRequest.getUserName());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        }catch (Exception exception){
            throw new Exception("Invalid user name and password");
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(authRequest.getUserName());
        return new ResponseEntity<>(jwtUtil.generateToken(userDetails),HttpStatus.OK);
    }
}

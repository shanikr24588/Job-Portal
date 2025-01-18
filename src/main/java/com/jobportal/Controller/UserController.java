package com.jobportal.Controller;


import com.jobportal.Dto.UserDto;
import com.jobportal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        // Log the received DTO for debugging
        System.out.println("Received userDto: " + userDto);

        // Register the user
        userDto = userService.registerUser(userDto);

        // Log the returned DTO
        System.out.println("Returning userDto: " + userDto);

        // Return the DTO wrapped in a ResponseEntity
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}

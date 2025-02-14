package com.jobportal.Controller;


import com.jobportal.Dto.LoginDto;
import com.jobportal.Dto.ResponseDto;
import com.jobportal.Dto.UserDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) throws JobPortalException {
        // Log the received DTO for debugging
        System.out.println("Received userDto: " + userDto);

        // Register the user
        userDto = userService.registerUser(userDto);

        // Log the returned DTO
        System.out.println("Returning userDto: " + userDto);

        // Return the DTO wrapped in a ResponseEntity
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody @Valid LoginDto loginDto) throws JobPortalException {

        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);


    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDto>sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception {

        userService.sendOtp(email);

        return new ResponseEntity<>(new ResponseDto("OTP Sent Successfully."), HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDto> verifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email, @PathVariable @Pattern(regexp="^[0-9]{6}$", message = "{otp.invalid}") String otp) throws JobPortalException{
        userService.verifyOtp(email, otp);
        return new ResponseEntity<>(new ResponseDto("OTP has been Verified. "), HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDto>changePassword(@RequestBody @Valid LoginDto loginDto) throws JobPortalException{
        return new ResponseEntity<>(userService.changePassword(loginDto), HttpStatus.OK);
    }


}

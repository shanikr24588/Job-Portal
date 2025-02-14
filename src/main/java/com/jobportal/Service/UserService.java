package com.jobportal.Service;

import com.jobportal.Dto.LoginDto;
import com.jobportal.Dto.ResponseDto;
import com.jobportal.Dto.UserDto;
import com.jobportal.Exception.JobPortalException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDto registerUser(UserDto userDto) throws JobPortalException;
    public UserDto getUserByEmail(String email) throws JobPortalException;

   public UserDto loginUser(LoginDto loginDto) throws JobPortalException;

   public Boolean sendOtp(String email) throws Exception;

    public  Boolean verifyOtp(String email, String otp) throws JobPortalException;

    public ResponseDto changePassword(LoginDto loginDto) throws  JobPortalException;
}

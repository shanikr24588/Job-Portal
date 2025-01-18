package com.jobportal.Service;

import com.jobportal.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDto registerUser(UserDto userDto);
}

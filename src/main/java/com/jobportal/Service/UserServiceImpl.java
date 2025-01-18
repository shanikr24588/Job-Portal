package com.jobportal.Service;

import com.jobportal.Dto.UserDto;
import com.jobportal.Entity.User;
import com.jobportal.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userDto.toEntity();
        user = userRepository.save(user);
        return user.toDto();


    }
}

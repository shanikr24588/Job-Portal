package com.jobportal.Controller;

import com.jobportal.Dto.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto>getProfile(@PathVariable Long id) throws JobPortalException{
        ProfileDto profile = profileService.getProfile(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/recommendTalent")
    public ResponseEntity<List<ProfileDto>> recommentTalent()throws  JobPortalException{
        List<ProfileDto> profileDtos = profileService.recommendTlent();
        return new ResponseEntity<>(profileDtos, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto>updateProfile(@RequestBody ProfileDto profileDto) throws JobPortalException{
        ProfileDto updatedProfile = profileService.updateProfile(profileDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

}

package com.jobportal.Service;

import com.jobportal.Dto.ProfileDto;
import com.jobportal.Entity.Profile;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Repository.ProfileRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());

        profileRepository.save(profile);
        return profile.getId();


    }

    @Override
    public ProfileDto getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND")).toDto();
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException {
        profileRepository.findById(profileDto.getId()).orElseThrow(()->new JobPortalException("PROFILE_NOT_FOUND"));
        Profile updatedProfile = profileRepository.save(profileDto.toEntity());
        return updatedProfile.toDto();
    }

    @Override
    public List<ProfileDto> recommendTlent() throws JobPortalException {
        List<ProfileDto> list = profileRepository.findAll().stream().map((x) -> x.toDto()).toList();
        return list;
    }
}

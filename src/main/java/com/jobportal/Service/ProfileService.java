package com.jobportal.Service;

import com.jobportal.Dto.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    public Long createProfile(String email) throws JobPortalException;
    public ProfileDto getProfile(Long id) throws JobPortalException;
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException;
    public List<ProfileDto> recommendTlent() throws JobPortalException;
}

package com.jobportal.Service;

import com.jobportal.Dto.ApplicantDto;
import com.jobportal.Dto.Application;
import com.jobportal.Dto.JobDto;
import com.jobportal.Dto.ResponseDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
public interface JobService {


    public JobDto postJob(@Valid JobDto jobDto) throws JobPortalException;

    public List<JobDto> getAllJobs() throws JobPortalException;

    public JobDto getJob(Long id) throws JobPortalException;

    public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException;

    public List<JobDto> getJobsPostedBy(Long id) throws JobPortalException;

    public void changeAppStatus(Application application) throws JobPortalException;
}

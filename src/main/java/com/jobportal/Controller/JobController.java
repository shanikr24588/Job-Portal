package com.jobportal.Controller;

import com.jobportal.Dto.ApplicantDto;
import com.jobportal.Dto.Application;
import com.jobportal.Dto.JobDto;
import com.jobportal.Dto.ResponseDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post")
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobDto jobDto)throws JobPortalException {
        JobDto job = jobService.postJob(jobDto);

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<JobDto>> getAllJobs() throws JobPortalException{
        List<JobDto> allJobs = jobService.getAllJobs();
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto>getJob(@PathVariable Long id) throws JobPortalException{
        JobDto job = jobService.getJob(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDto> applyJob(@PathVariable Long id , @RequestBody ApplicantDto applicantDto) throws JobPortalException{
        jobService.applyJob(id, applicantDto);
        return new ResponseEntity<>(new ResponseDto("Applied Successfully"), HttpStatus.OK);
    }

    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobDto>>getJobsPostedBy(@PathVariable Long id) throws JobPortalException{
        List<JobDto> jobsPostedBy = jobService.getJobsPostedBy(id);
        return new ResponseEntity<>(jobsPostedBy, HttpStatus.OK);
    }

    @PostMapping("/changeAppStatus")
    public ResponseEntity<ResponseDto>changeAppStatus(@RequestBody Application application) throws JobPortalException{
        jobService.changeAppStatus(application);
        return new ResponseEntity<>(new ResponseDto("Application Status Changed Successfully"), HttpStatus.OK);
    }

}

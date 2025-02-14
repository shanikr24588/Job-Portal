package com.jobportal.Service;

import com.jobportal.Dto.*;
import com.jobportal.Entity.Applicant;
import com.jobportal.Entity.Job;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Repository.JobRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public JobDto postJob(JobDto jobDto) throws JobPortalException {
        if (jobDto.getId() == 0){
            jobDto.setId(Utilities.getNextSequence("jobs"));
            jobDto.setPosTime(LocalDateTime.now());
            NotificationDto notiDto = new NotificationDto();
            notiDto.setAction("Job Posted");
            notiDto.setMessage("Job Posted Successfully for "+jobDto.getJobTitle()+" at "+ jobDto.getCompany());

            notiDto.setUserId(jobDto.getPostedBy());
            notiDto.setRoute("/posted-jobs/"+jobDto.getId());
            try {
                notificationService.sendNotification(notiDto);
            } catch (JobPortalException e) {
                throw new RuntimeException(e);
            }
        } else {
            Job job = jobRepository.findById(jobDto.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
            if (job.getJobStatus().equals(JobStatus.DRAFT) || jobDto.getJobStatus().equals(JobStatus.CLOSED)){
                jobDto.setPosTime(LocalDateTime.now());
            }
        }

        Job job = jobRepository.save(jobDto.toEntity());
        return job.toDto();
    }

    @Override
    public List<JobDto> getAllJobs() throws JobPortalException {
//      jobRepository.findAll().stream().map(Job::toDto).toList();

        return jobRepository.findAll().stream().map((x)->x.toDto()).toList();
    }

    @Override
    public JobDto getJob(Long id) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        return job.toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants = job.getApplicant();
        if(applicants == null) {
            applicants = new ArrayList<>();
        }

        if(applicants.stream().filter((x)->x.getApplicantId()==applicantDto.getApplicantId()).toList().size()>0) throw new JobPortalException("JOB_APPLIED_ALREADY");
        applicantDto.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantDto.toEntity());
        job.setApplicant(applicants);
        jobRepository.save(job);


    }

    @Override
    public List<JobDto> getJobsPostedBy(Long id) throws JobPortalException {
        List<JobDto> joblist = jobRepository.findByPostedBy(id).stream().map((x) -> x.toDto()).toList();
        return joblist;
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        Job job = jobRepository.findById(application.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT-FOUND"));
        List<Applicant>applicants = job.getApplicant().stream().map((x)->{
            if (application.getApplicantId() == x.getApplicantId()){
                x.setApplicationStatus(application.getApplicationStatus());
                if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)){
                    x.setInterViewTime(application.getInterviewTime());
                    NotificationDto notiDto = new NotificationDto();
                    notiDto.setAction("Interview Scheduled");
                    notiDto.setMessage("Interview scheduled for job id"+application.getId());
                    notiDto.setUserId(application.getApplicantId());
                    try {
                        notificationService.sendNotification(notiDto);
                    } catch (JobPortalException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            return x;
        }).toList();
        job.setApplicant(applicants);
        jobRepository.save(job);

    }
}

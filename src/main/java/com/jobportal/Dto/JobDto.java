package com.jobportal.Dto;

import com.jobportal.Entity.Applicant;
import com.jobportal.Entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jobportal.Dto.ApplicantDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private Long id;
    private String jobTitle;
    private String company;
     private List<ApplicantDto> applicant;
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime posTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;


    public Job toEntity(){
        return new Job(
                this.id,
                this.jobTitle,
                this.company,
                this.applicant!=null?this.applicant.stream().map((x)->x.toEntity()).toList() :null,
                this.about,
                this.experience,
                this.jobType,
                this.location,
                this.packageOffered,
                this.posTime,
                this.description,
                this.skillsRequired,
                this.jobStatus,
                this.postedBy
        );
    }
}

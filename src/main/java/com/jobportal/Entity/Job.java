package com.jobportal.Entity;


import com.jobportal.Entity.Applicant;
import com.jobportal.Dto.JobDto;
import com.jobportal.Dto.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class Job {
    @Id
    private Long id;
    private String jobTitle;
    private String company;
    private List<Applicant> applicant;
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

    public JobDto toDto(){
        return new JobDto(
                this.id,
                this.jobTitle,
                this.company,
                this.applicant!=null?this.applicant.stream().map((x)->x.toDto()).toList():null,
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

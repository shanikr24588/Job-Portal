package com.jobportal.Dto;

import com.jobportal.Entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String name;
    private String email;
    private String JobTitle;
    private String company;
    private String location;
    private String about;
    private String picture;
    private Long totalExp;
    private List<String> skills;
    private List<Experience>experiences;
    private List<Certification>certifications;
    private List<Long> savedJobs;

    public Profile toEntity(){
        return new Profile(
                this.id,
                this.name,
                this.email,
                this.JobTitle,
                this.company,
                this.location,
                this.about,
                this.picture!=null?Base64.getDecoder().decode(this.picture):null,
                this.totalExp,
                this.skills,
                this.experiences,
                this.certifications,
                this.savedJobs

        );
    }
}



package com.jobportal.Entity;

import com.jobportal.Dto.Certification;
import com.jobportal.Dto.Experience;
import com.jobportal.Dto.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.cert.Certificate;
import java.util.Base64;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile")
public class Profile {
    @Id
    private Long id;
    private String name;
    private String email;
    private String JobTitle;
    private String company;
    private String location;
    private String about;
    private byte[] picture;
    private Long totalExp;
    private List<String> skills;
    private List<Experience>experiences;
    private List<Certification>certifications;
    private List<Long>savedJobs;

    public ProfileDto toDto(){
        return new ProfileDto(this.id, this.name, this.email, this.JobTitle, this.company, this.location, this.about, this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null,
        this.totalExp, this.skills, this.experiences, this.certifications, this.savedJobs
        );
    }

}



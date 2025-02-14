package com.jobportal.Dto;

import com.jobportal.Entity.User;

import com.jobportal.Enum.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank(message = "{user.name.absent}")
    private String name;
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;
    @NotBlank(message = "user.password.absent")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "{user.password.invalid}")
    private String password;
    private AccountType accountType;
    private Long profileId;

    public UserDto(Long id, String name, String email, String password, AccountType accountType, Long profileId){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.profileId = profileId;

    }

    public User toEntity(){
        return new User(this.id, this.name, this.email, this.password, this.accountType, this.profileId);
    }
}

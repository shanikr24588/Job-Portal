package com.jobportal.Entity;


import com.jobportal.Enum.AccountType;
import com.jobportal.Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Data
@Document(collection="users")
public class User {
    @Id
    private Long id;

    private String name;

    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;
    private Long profileId;

    public User(Long id, String name, String email, String password, AccountType accountType, Long profileId){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.profileId = profileId;

    }



    public UserDto toDto(){
        return new UserDto(this.id, this.name, this.email, this.password, this.accountType, this.profileId);
    }
}

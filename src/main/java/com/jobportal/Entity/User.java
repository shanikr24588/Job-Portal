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
    private String id;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public @NonNull String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NonNull String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public AccountType getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(AccountType accountType) {
//        this.accountType = accountType;
//    }

    private String name;
    @NonNull
    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;

    public User(String id, String name, String email, String password, AccountType accountType){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;

    }



    public UserDto toDto(){
        return new UserDto(this.id, this.name, this.email, this.password, this.accountType);
    }
}

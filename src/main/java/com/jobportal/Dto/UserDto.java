package com.jobportal.Dto;

import com.jobportal.Entity.User;

import com.jobportal.Enum.AccountType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class UserDto {
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
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

    private String id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;

    public UserDto(String id, String name, String email, String password, AccountType accountType){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;

    }

    public User toEntity(){
        return new User(this.id, this.name, this.email, this.password, this.accountType);
    }
}

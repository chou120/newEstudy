package com.easy.zadmin.pojo.dto;

import com.easy.zadmin.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * @Author sanye
 * @Date 2023/9/19 22:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUser  implements Serializable {
    private User user;
    private String firstPwd;
    private String secondPwd;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstPwd() {
        return firstPwd;
    }

    public void setFirstPwd(String firstPwd) {
        this.firstPwd = firstPwd;
    }

    public String getSecondPwd() {
        return secondPwd;
    }

    public void setSecondPwd(String secondPwd) {
        this.secondPwd = secondPwd;
    }
}

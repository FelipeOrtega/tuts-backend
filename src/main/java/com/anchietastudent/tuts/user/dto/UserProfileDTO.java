package com.anchietastudent.tuts.user.dto;

import com.anchietastudent.tuts.course.dto.CourseDTO;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.user.model.Role;
import com.anchietastudent.tuts.user.model.User;

import java.text.SimpleDateFormat;

public class UserProfileDTO {

    public UserProfileDTO() {

    }

    public UserProfileDTO(String name, String email, String birthDate, String phone, String userType) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
        this.userType = userType;
    }

    private String name;
    private String email;
    private String birthDate;
    private String phone;
    private String userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static UserProfileDTO buildDTO(User user, Role role) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        dto.setBirthDate(fmt.format(user.getBirthDate()));
        dto.setUserType(role.getName().getTranslatedName());
        return dto;
    }
}

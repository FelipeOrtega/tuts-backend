package com.anchietastudent.tuts.course.dto;

import com.sun.istack.NotNull;

import java.util.UUID;

public class CourseFilterDTO {

    public CourseFilterDTO() {
    }

    @NotNull
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}

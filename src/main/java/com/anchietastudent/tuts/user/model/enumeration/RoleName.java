package com.anchietastudent.tuts.user.model.enumeration;

public enum RoleName {

    STUDENT("Estudante"), TEACHER("Professor");

    private String translatedName;

    RoleName(String translatedName) {
        this.translatedName = translatedName;
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }
}

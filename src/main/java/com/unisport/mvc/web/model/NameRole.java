package com.unisport.mvc.web.model;

public enum NameRole {

    MANAGER("Менеджер"),
    DEVELOPER("Разработчик"),
    TEAMLEAD("СтаршийРазработчик");

    private String name;

    private NameRole(String name) {
        this.name = name;
    }

    public String getNameRole() {
        return name();
    }
}

package org.example.system_dto;

import lombok.*;


public class SystemRoot {
    public Initialization initialization;
    public ApiList apiList;

    public SystemRoot() {
    }

    public Initialization getInitialization() {
        return initialization;
    }

    public void setInitialization(Initialization initialization) {
        this.initialization = initialization;
    }

    public ApiList getApiList() {
        return apiList;
    }

    public void setApiList(ApiList apiList) {
        this.apiList = apiList;
    }

    @Override
    public String toString() {
        return "SystemRoot{" +
                "initialization=" + initialization +
                ", apiList=" + apiList +
                '}';
    }
}

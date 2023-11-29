package org.example.system_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


public class ApiList {
    public List<Api> apiList = new ArrayList<>();

    public ApiList() {
    }

    public List<Api> getApiList() {
        return apiList;
    }

    public void setApiList(List<Api> apiList) {
        this.apiList = apiList;
    }

    @Override
    public String toString() {
        return "ApiList{" +
                "apiList=" + apiList +
                '}';
    }
}

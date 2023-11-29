package org.example.system_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


public class Initialization {
    public List<String> ddls = new ArrayList<>();

    public List<String> getDdls() {
        return ddls;
    }

    public void setDdls(List<String> ddls) {
        this.ddls = ddls;
    }

    public Initialization() {
    }

    @Override
    public String toString() {
        return "Initialization{" +
                "ddls=" + ddls +
                '}';
    }
}

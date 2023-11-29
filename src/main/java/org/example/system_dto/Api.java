package org.example.system_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Api {
    public String route;
    public String httpMethod;
    public Dto dto;

    public HashMap<String,String> sqlQueriesList = new HashMap<>();
    public String javaCodelet;

    public Api() {
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Dto getDto() {
        return dto;
    }

    public void setDto(Dto dto) {
        this.dto = dto;
    }

    public HashMap<String, String> getSqlQueriesList() {
        return sqlQueriesList;
    }

    public void setSqlQueriesList(HashMap<String, String> sqlQueriesList) {
        this.sqlQueriesList = sqlQueriesList;
    }

    public String getJavaCodelet() {
        return javaCodelet;
    }

    public void setJavaCodelet(String javaCodelet) {
        this.javaCodelet = javaCodelet;
    }

    @Override
    public String toString() {
        return "Api{" +
                "route='" + route + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", dto=" + dto +
                ", sqlQueriesList=" + sqlQueriesList +
                ", javaCodelet='" + javaCodelet + '\'' +
                '}';
    }


    public static class Dto{
        public HashMap<String, String> request =new HashMap<>();
        public String response;



        public Dto() {
        }

        public HashMap<String, String> getRequest() {
            return request;
        }

        public void setRequest(HashMap<String, String> request) {
            this.request = request;
        }

        public String getResponse() {
            return response;
        }

        @Override
        public String toString() {
            return "Dto{" +
                    "request=" + request +
                    ", response='" + response + '\'' +
                    '}';
        }

        public void setResponse(String response) {
            this.response = response;
        }


    }


    public static class DtoParam{
        public String name;
        public String dataType;

        public DtoParam() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        @Override
        public String toString() {
            return "DtoParam{" +
                    "name='" + name + '\'' +
                    ", dataType='" + dataType + '\'' +
                    '}';
        }
    }
}

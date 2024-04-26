package com.health.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    private String storage;
    private String name;
    private String url;
    private String base64Content;
    private Integer Size;
    private String type;
    private String originalName;
    private String hash;

    private void setUrl(String url){
        String[] data = url.split(",");
        this.base64Content = data[1];
    }
}

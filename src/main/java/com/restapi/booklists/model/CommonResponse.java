package com.restapi.booklists.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    private String status;
    private Object resultData;
    private Object resultMessage;
}

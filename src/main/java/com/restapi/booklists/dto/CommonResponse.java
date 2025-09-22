package com.restapi.booklists.dto;

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

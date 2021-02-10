package com.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalityResponseDto {

    private Long id;

    private String localityName;

    private String countyName;

}

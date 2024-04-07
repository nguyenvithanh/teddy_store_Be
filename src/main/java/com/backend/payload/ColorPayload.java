package com.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorPayload {
    String idColor;
    String color;
    Integer pageColor;
    Integer sizeColor;
}

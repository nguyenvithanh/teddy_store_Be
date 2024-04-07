package com.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizePayload {
    String idSize;
    String sizeNo;
    Integer pageSize;
    Integer sizeSize;
}

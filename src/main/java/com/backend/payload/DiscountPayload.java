package com.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPayload {
    String id;
    Double discount;
    String startDate;
    String endDate;
    String productId;
    Integer page;
    Integer size;

    String searchDateFrom;
    String searchDateTo;
}

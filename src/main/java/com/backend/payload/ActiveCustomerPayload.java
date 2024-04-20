package com.backend.payload;

import lombok.Data;

@Data
public class ActiveCustomerPayload {
    private String id;
    private Boolean active;
}

package com.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPayload {
    String name;
    Integer phone;
    Date dob;
    String email;
    Boolean gender;
    String username;
    String password;
    String confirmPassword;
}

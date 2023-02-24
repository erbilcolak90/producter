package com.productuer.testcase.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String id;
    private String username;
    private String email;
    private String password;
}

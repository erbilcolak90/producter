package com.producter.testcase.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamInput {

    private String id;
    private String name;
    private int capacity;
}

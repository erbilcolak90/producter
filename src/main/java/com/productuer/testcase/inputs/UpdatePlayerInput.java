package com.productuer.testcase.inputs;

import com.productuer.testcase.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlayerInput {

    private String id;
    private String name;
    private String surname;
    private Position position;
}

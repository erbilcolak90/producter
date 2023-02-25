package com.producter.testcase.inputs;

import com.producter.testcase.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInput {

    private int page;
    private int size;
    private String fieldName;
    private SortType sortBy;

}

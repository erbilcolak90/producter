package com.productuer.testcase.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @Column(name = "id")
    private String id;

    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "deleted")
    private boolean isDeleted;
    @Column(name = "created_date")
    private OffsetDateTime createdDate;
    @Column(name = "update_date")
    private OffsetDateTime updateDate;
}

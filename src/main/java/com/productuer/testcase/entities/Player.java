package com.productuer.testcase.entities;

import com.productuer.testcase.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "id")
    private String id;


    @NotNull
    @NotBlank(message = "name cannot blank")
    @NotEmpty(message = "name cannot empty")
    @Column(name = "name")
    private String name;


    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "surname")
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "deleted")
    private boolean isDeleted;

    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss.SSS'Z'")
    private OffsetDateTime createdDate;

    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss.SSS'Z'")
    private OffsetDateTime updateDate;
}

package com.productuer.testcase.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "deleted")
    private boolean isDeleted;
    @Column(name = "created_date")
    private OffsetDateTime createdDate;
    @Column(name = "update_date")
    private OffsetDateTime updateDate;

}

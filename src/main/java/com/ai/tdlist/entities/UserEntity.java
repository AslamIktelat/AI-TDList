package com.ai.tdlist.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    private int id;
    private String username;
    private String email;
    private String password;
}

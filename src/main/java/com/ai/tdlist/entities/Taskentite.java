package com.ai.tdlist.entities;

import com.ai.tdlist.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "Tasks")
public class Taskentite {
    @Id //Primary key - Unique & Not Null
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Name;
    private String Description;
    private Date Date;
    private Time Time;
    private String Status;
}

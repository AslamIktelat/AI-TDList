package com.ai.tdlist.dtos;

import com.ai.tdlist.enums.TaskStatus;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class Task {
    private String Name;
    private int Id;
    private String description;
    private Date date;
    private Time time;
    private TaskStatus status;
}

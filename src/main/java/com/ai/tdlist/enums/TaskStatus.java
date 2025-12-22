package com.ai.tdlist.enums;

public enum TaskStatus {
    TODO("TODO"),
    INPROGRESS("INPROGRESS"),
    DONE("DONE");

    private final String status;
    TaskStatus(String status) {
        this.status = status;
    }
    public String getStatusValue() {
        return status;
    }
}

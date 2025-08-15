package com.codeviewandtalk.library.management.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="audit_log")
public class ArchiveAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime runTime;

    private int recordsArchived;

    private String status; // SUCCESS / FAILED

    private String message;

    public ArchiveAuditLog() {}

    public ArchiveAuditLog(LocalDateTime runTime, int recordsArchived, String status, String message) {
        this.runTime = runTime;
        this.recordsArchived = recordsArchived;
        this.status = status;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRunTime() {
        return runTime;
    }

    public void setRunTime(LocalDateTime runTime) {
        this.runTime = runTime;
    }

    public int getRecordsArchived() {
        return recordsArchived;
    }

    public void setRecordsArchived(int recordsArchived) {
        this.recordsArchived = recordsArchived;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

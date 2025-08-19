package com.codeviewandtalk.library.management.repository;

import com.codeviewandtalk.library.management.model.ArchiveAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveAuditLogRepository extends JpaRepository<ArchiveAuditLog,Long> {
}

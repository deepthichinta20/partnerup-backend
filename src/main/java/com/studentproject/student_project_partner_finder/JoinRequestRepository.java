
package com.studentproject.student_project_partner_finder;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRequestRepository
        extends JpaRepository<JoinRequest, Long> {

    List<JoinRequest> findByProjectId(Long projectId);
}

package com.studentproject.student_project_partner_finder;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/join-requests")
public class JoinRequestController {
    @PatchMapping("/{id}/status")
public JoinRequest updateStatus(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    JoinRequest request = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Request not found"));

    String status = body.get("status");

    if (!"ACCEPTED".equals(status)
            && !"REJECTED".equals(status)) {
        throw new IllegalArgumentException(
                "Status must be ACCEPTED or REJECTED");
    }

    request.setStatus(status);

    return repository.save(request);
}

    private final JoinRequestRepository repository;

    public JoinRequestController(JoinRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public JoinRequest createRequest(
            @RequestBody JoinRequest request) {
        return repository.save(request);
    }

    @GetMapping
    public List<JoinRequest> getAllRequests() {
        return repository.findAll();
    }

    @GetMapping("/project/{projectId}")
    public List<JoinRequest> getRequestsForProject(
            @PathVariable Long projectId) {
        return repository.findByProjectId(projectId);
    }
}
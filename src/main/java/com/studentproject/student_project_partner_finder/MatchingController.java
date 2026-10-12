
package com.studentproject.student_project_partner_finder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MatchingController {

    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;

    public MatchingController(
            StudentRepository studentRepository,
            ProjectRepository projectRepository) {
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/matching/project/{projectId}")
    public List<MatchResult> findMatchingStudents(
            @PathVariable Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        String requiredSkills = project.getRequiredSkills();

        List<MatchResult> results = new ArrayList<>();

        if (requiredSkills == null || requiredSkills.isBlank()) {
            return results;
        }

        String[] skills = requiredSkills.toLowerCase(Locale.ROOT)
                .split(",");

        List<Student> students = studentRepository.findAll();

        for (Student student : students) {

            if (student.getSkills() == null) {
                continue;
            }

            String studentSkills =
                    student.getSkills().toLowerCase(Locale.ROOT);

            int matchedSkills = 0;
            int totalSkills = 0;

            for (String skill : skills) {
                String trimmedSkill = skill.trim();

                if (!trimmedSkill.isEmpty()) {
                    totalSkills++;

                    if (studentSkills.contains(trimmedSkill)) {
                        matchedSkills++;
                    }
                }
            }

            if (matchedSkills > 0 && totalSkills > 0) {
                int percentage =
                        (matchedSkills * 100) / totalSkills;

                results.add(new MatchResult(student, percentage));
            }
        }

        results.sort(
                Comparator.comparingInt(
                        MatchResult::getMatchPercentage)
                        .reversed());

        return results;
    }
}



package com.studentproject.student_project_partner_finder;

public class MatchResult {

    private Student student;
    private int matchPercentage;

    public MatchResult(Student student, int matchPercentage) {
        this.student = student;
        this.matchPercentage = matchPercentage;
    }

    public Student getStudent() {
        return student;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }
}


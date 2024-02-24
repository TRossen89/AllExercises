package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StudentInfo {

    private String fullName;

    private Integer studentId;
    private String thisSemesterName;

    private String thisSemesterDescription;

    public StudentInfo(String fullName, Integer studentId, String thisSemesterName, String thisSemesterDescription) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }
}

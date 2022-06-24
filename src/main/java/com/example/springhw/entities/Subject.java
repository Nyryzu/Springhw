package com.example.springhw.entities;

import lombok.*;
import javax.persistence.*;
import java.util.*;
import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = Subject.TBL_name)

public class Subject extends AbstractEntity<Long>{

    public static final String TBL_name = "subjects";
    public static final String FLD_id = "subject_id";
    public static final String FLD_name = "subject_name";
    public static final String FLD_RS = "registered_students";

    @Column(name=FLD_name,nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "Subject_Students",
            joinColumns = @JoinColumn(name = "subjectsSubjectId", referencedColumnName = FLD_ID),
            inverseJoinColumns = @JoinColumn(name = "studentId", referencedColumnName = FLD_ID))
    private Set<Student> registeredStudents = new LinkedHashSet<>();

    private int grade;


}

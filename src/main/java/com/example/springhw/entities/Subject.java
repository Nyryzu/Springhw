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

public class Subject {

    public static final String TBL_name = "subjects";
    public static final String FLD_id = "subject_id";
    public static final String FLD_name = "subject_name";
    public static final String FLD_RS = "registered_students";

    @Column(name = FLD_id,updatable = false,nullable = false,unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name=FLD_name,nullable = false)
    private String name;

    @Column(name = FLD_RS)
    @ManyToMany(mappedBy = "subjects",cascade = ALL)
    private Set<Student> registeredStudents = new HashSet<>();

    private int grade;


}

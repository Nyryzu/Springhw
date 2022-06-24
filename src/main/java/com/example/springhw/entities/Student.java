package com.example.springhw.entities;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = Student.TBL_name)
public class Student extends AbstractEntity<Long>{

    public static final String TBL_name = "students";
    public static final String FLD_id = "studentId";
    public static final String FLD_name = "student_name";
    public static final String FLD_NC = "Neptun_Code";
    public static final String FLD_JT = "Subject_Students";


    @Column(name = FLD_name)
    @NotNull
    private String name;

    @Column(name = FLD_NC)
    @NotNull
    private String neptunCode;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column
    @ManyToMany(mappedBy = "registeredStudents")
    private Set<Subject> subjects = new LinkedHashSet<>();

}

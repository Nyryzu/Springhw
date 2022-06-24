package com.example.springhw.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = Classroom.TBL_name)

public class Classroom extends AbstractEntity<Long>{
    public static final String TBL_name = "classrooms";
    public static final String FLD_id = "classroomId";
    public static final String FLD_enrolledStu = "class_students";

    @Column
    @OneToMany
    @JoinTable(name = FLD_enrolledStu,
            joinColumns = @JoinColumn(name = FLD_id),
            inverseJoinColumns = @JoinColumn(name = Student.FLD_id))
    private Set<Student> enrolledStudents = new java.util.LinkedHashSet<>();

    public void setEnrolledStudents(Student student) {
        this.enrolledStudents.add(student);
    }
}

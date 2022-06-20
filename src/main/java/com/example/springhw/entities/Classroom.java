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

public class Classroom {
    public static final String TBL_name = "classrooms";
    public static final String FLD_id = "class_id";
    public static final String FLD_enrolledStu = "class_students";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =FLD_id ,updatable = false,nullable = false,unique = true)
    private Long id;

    @OneToMany
    @JoinTable(name = FLD_enrolledStu,
            joinColumns = @JoinColumn(name = FLD_id),
            inverseJoinColumns = @JoinColumn(name = Student.FLD_id))
    private Set<Student> enrolledStudents = new java.util.LinkedHashSet<>();


}

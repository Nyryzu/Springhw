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
public class Student {

    public static final String TBL_name = "students";
    public static final String FLD_id = "student_id";
    public static final String FLD_name = "student_name";
    public static final String FLD_NC = "Neptun_Code";
    public static final String FLD_JT = "Subject_Students";

    @Id
    @Column(name = FLD_id ,updatable = false,nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FLD_name)
    @NotNull
    private String Name;

    @Column(name = FLD_NC)
    @NotNull
    private String neptunCode;

    @ManyToMany
    @JoinTable(name = FLD_JT,
            joinColumns = @JoinColumn(name = FLD_id, referencedColumnName = Subject.FLD_id))
    private Set<Subject> subjects = new LinkedHashSet<>();

}

package np.com.esewa.learn.springcaching.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;
    private String studentName;
    private int studentAge;

    @Enumerated(EnumType.STRING)
    private Status status;
}

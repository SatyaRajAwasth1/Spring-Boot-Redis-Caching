package np.com.esewa.learn.springcaching.repository;

import np.com.esewa.learn.springcaching.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

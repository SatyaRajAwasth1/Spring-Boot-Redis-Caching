package np.com.esewa.learn.springcaching.service;

import np.com.esewa.learn.springcaching.resource.StudentDeletedResponse;
import np.com.esewa.learn.springcaching.resource.StudentRequest;
import np.com.esewa.learn.springcaching.resource.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    // inserting new record
    StudentResponse addStudent(StudentRequest student);
    List<StudentResponse> getAllStudent();
    StudentResponse getStudentById(Long id);
    StudentResponse updateStudent(StudentRequest studentRequest, Long studentId);
    StudentDeletedResponse deleteStudent(Long studentId);
}
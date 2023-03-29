package np.com.esewa.learn.springcaching.service;

import np.com.esewa.learn.springcaching.model.Status;
import np.com.esewa.learn.springcaching.model.Student;
import np.com.esewa.learn.springcaching.resource.StudentDeletedResponse;
import np.com.esewa.learn.springcaching.resource.StudentRequest;
import np.com.esewa.learn.springcaching.resource.StudentResponse;
import np.com.esewa.learn.springcaching.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentAge(studentRequest.getAge());
        student.setStudentName(studentRequest.getName());
        student.setStatus(Status.INACTIVE);
        Student save = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(save.getStudentName());
        studentResponse.setAge(save.getStudentAge());
        return studentResponse;
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        List<StudentResponse> studentsResponseList = new ArrayList<>();
        List<Student> studentsList = studentRepository.findAll();

        for (Student student: studentsList) {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setAge(student.getStudentAge());
            studentResponse.setName(student.getStudentName());

            studentsResponseList.add(studentResponse);
        }
        return studentsResponseList;
    }

    @Override
    @Cacheable(value = {"studentCache"}, key = "#studentId") //cache for get request
    public StudentResponse getStudentById(Long studentId){
        StudentResponse studentResponse = new StudentResponse();

        Student retrievedStudent  = studentRepository.findById(studentId).orElse(null) ;
        if (retrievedStudent != null){
            studentResponse.setName(retrievedStudent.getStudentName());
            studentResponse.setAge(retrievedStudent.getStudentAge());
        }
        else {
            studentResponse.setAge(null);
            studentResponse.setName(null);
        }
        return studentResponse;
    }

    @Override
    @CachePut(cacheNames = {"studentCache"}, key = "#studentId")
    public StudentResponse updateStudent(StudentRequest studentRequest, Long studentId) {
        Student student = studentRepository.getReferenceById(studentId);
        student.setStudentName(studentRequest.getName());
        student.setStudentAge(studentRequest.getAge());
        student.setStatus(Status.ACTIVE);

        Student updatedStudent = studentRepository.save(student); // updated student in database and get updated student

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setAge(updatedStudent.getStudentAge());
        studentResponse.setName(updatedStudent.getStudentName());

        return studentResponse;
    }

    @Override
    @CacheEvict(cacheNames = {"studentCache"}, key = "#studentId")
    public StudentDeletedResponse deleteStudent(Long studentId) {
        Student studentToBeDeleted = studentRepository.findById(studentId).orElseThrow(null);
        //assert studentToBeDeleted != null;
        studentToBeDeleted.setStatus(Status.DELETED);
        studentRepository.save(studentToBeDeleted);
        StudentDeletedResponse studentDeletedResponse = new StudentDeletedResponse();
        studentDeletedResponse.setMessage("Student Deleted successfully");
        return studentDeletedResponse;
    }
}

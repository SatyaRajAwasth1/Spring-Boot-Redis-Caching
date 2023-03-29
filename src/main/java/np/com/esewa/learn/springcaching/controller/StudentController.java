package np.com.esewa.learn.springcaching.controller;

import np.com.esewa.learn.springcaching.resource.StudentDeletedResponse;
import np.com.esewa.learn.springcaching.resource.StudentRequest;
import np.com.esewa.learn.springcaching.resource.StudentResponse;
import np.com.esewa.learn.springcaching.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    private final StudentService studentService ;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //get all students
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents(){
        // returns list of Student Response
        return studentService.getAllStudent();
    }

    @PostMapping("/students") // takes course by default in JSON Format
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest){ // content resides in body in POST method
        return studentService.addStudent(studentRequest);
    }

    @DeleteMapping("/students/delete/{studentId}")
    public StudentDeletedResponse deleteStudent(@PathVariable Long studentId){ // delete student
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/students/{studentId}")
    public StudentResponse  getStudent(@PathVariable long studentId){
            return studentService.getStudentById(studentId);
        }


    @PutMapping("/students/update/{studentId}")
    public StudentResponse updateStudent(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentRequest, studentId);
    }

}

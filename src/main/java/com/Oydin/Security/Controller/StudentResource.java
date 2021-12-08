package com.Oydin.Security.Controller;

import com.Oydin.Security.Entity.Student;
import com.Oydin.Security.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public Student save(@RequestBody Student student){
        return studentService.create(student);
    }
    @GetMapping("/getAll")
    public List<Student> getAll(){
        List<Student> students = studentService.getAll();
        return students;
    }
    @GetMapping("/{studentId}")
    public Student getById(@PathVariable String studentId){
        return studentService.findById(studentId);
    }
    @PutMapping("/update")
    public Student update(@RequestBody Student student,@PathVariable String studentId){
        return studentService.update(student,studentId);
    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Student student,@PathVariable String studentId){
        studentService.delete(student,studentId);
       return ResponseEntity.ok("student delete succesfuly");
    }
}

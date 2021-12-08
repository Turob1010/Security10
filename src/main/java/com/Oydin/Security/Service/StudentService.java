package com.Oydin.Security.Service;

import com.Oydin.Security.Entity.Student;
import com.Oydin.Security.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) {
        Student newStudent = studentRepository.save(student);
        return newStudent;
    }

    public Student findById(String studentId) {
        return studentRepository.findById(studentId).get();
    }
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student update(Student student, String studentId) {
        studentRepository.findById(studentId).get();
        Student updateStudent = studentRepository.save(student);
        return updateStudent;
    }

    public void delete(Student student, String studentId) {
        studentRepository.findById(studentId).get();
        studentRepository.delete(student);
    }
}
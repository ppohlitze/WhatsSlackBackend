package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Student;
import de.tub.ise.anwsys.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student s = new Student();
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        s.setStudies(student.getStudies());
        s = studentRepository.save(student);

        return ResponseEntity.ok(s);
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
}

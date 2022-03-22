package br.com.springestudo.inicio.endpoint;

import br.com.springestudo.inicio.error.CustomErrorType;
import br.com.springestudo.inicio.model.Student;
import br.com.springestudo.inicio.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndPoint {

    private final StudentRepository studentDao;

    public StudentEndPoint(StudentRepository dao) {
        this.studentDao = dao;
    }

    @GetMapping
    public ResponseEntity<?>  listAll(){
        return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Student> student;
        try {
            student = studentDao.findById(id);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(new CustomErrorType("Deu Erro na chamada."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student){
      try{
          studentDao.save(student);
      }catch (Exception e){
          return new ResponseEntity<>(new CustomErrorType("Inclusão não funcionou."), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Long id){
            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        try{
            studentDao.save(student);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType("Atualização não funcionou."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


}

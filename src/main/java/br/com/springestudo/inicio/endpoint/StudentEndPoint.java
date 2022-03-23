package br.com.springestudo.inicio.endpoint;

import br.com.springestudo.inicio.error.CustomErrorType;
import br.com.springestudo.inicio.error.ResourceMethodErrorException;
import br.com.springestudo.inicio.error.ResourceNotFoundException;
import br.com.springestudo.inicio.model.Student;
import br.com.springestudo.inicio.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

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
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Student not found for id:" + id);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student){
      try{
          studentDao.save(student);
      }catch (RuntimeException e){
          throw new ResourceMethodErrorException("Falha ao incluir registro.");
      }
      return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        try{
            studentDao.deleteById(id);
        }catch (Exception e){
            throw new ResourceMethodErrorException("Falha ao excluir registro.");
        }
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

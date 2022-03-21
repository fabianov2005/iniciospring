package br.com.springestudo.inicio.endpoint;

import br.com.springestudo.inicio.error.CustomErrorType;
import br.com.springestudo.inicio.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndPoint {

    @GetMapping
    public ResponseEntity<?>  listAll(){
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        try{
            Student student = new Student().getById(id);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<>(new CustomErrorType("Deu Erro na chamada."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Student().getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student){
      try{
          Student.studentList.add(student);
      }catch (Exception e){
          return new ResponseEntity<>(new CustomErrorType("Inclusão não funcionou."), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Student student){
            Student.studentList.remove(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        try{
            for (Student student1 : Student.studentList) {
                if(student.getId() == student1.getId()){
                    student1.setNome(student.getNome());
                }
            }
        }catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType("Atualização não funcionou."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


}

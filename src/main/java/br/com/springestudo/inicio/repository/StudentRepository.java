package br.com.springestudo.inicio.repository;

import br.com.springestudo.inicio.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNome(String nome);
}

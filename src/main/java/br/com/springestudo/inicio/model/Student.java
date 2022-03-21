package br.com.springestudo.inicio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {

    private int id;
    private String nome;
    public static List<Student> studentList;

    static {
        studentRepository();
    }

    public Student(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Student(String nome) {
        this.nome = nome;
    }

    public Student() {
    }

    private static void studentRepository() {
        List<Student> lista = new ArrayList<>();
        lista.add(new Student(1, "Fabiano"));
        lista.add(new Student(2, "Juliana"));

        studentList = lista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getById(int id) {
        Student student = new Student();
        for (Student st : studentList) {
            if (st.getId() == id) {
                student.setNome(st.getNome());
                student.setId(st.getId());
            }
        }
        return student;
    }

}
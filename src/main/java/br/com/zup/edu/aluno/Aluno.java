package br.com.zup.edu.aluno;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "Unique_aluno_matricula_data"
                , columnNames = {"matricula", "data"})
})
@Entity
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private LocalDate data;

    public Aluno(String nome, String cpf, String matricula, LocalDate data) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.data = data;
    }

    public Aluno() {
    }

    public Long getId() {
        return id;
    }
}



package br.com.zup.edu.aluno;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AlunoRequest {

    @NotBlank
    private String nome;

    @NotBlank @CPF
    private String cpf;

    @Size(max = 6)
    private String matricula;

    @PastOrPresent
    private LocalDate data;

    public AlunoRequest(String nome, String cpf, String matricula, LocalDate data) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.data = data;
    }

    public AlunoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getData() {
        return data;
    }

    public Aluno toModel() {
        return new Aluno(nome,cpf, matricula, data);
    }
}

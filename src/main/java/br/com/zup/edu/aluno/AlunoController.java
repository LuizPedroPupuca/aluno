package br.com.zup.edu.aluno;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @PostMapping("/aluno")
    public ResponseEntity<?> cadastra(@RequestBody @Valid AlunoRequest alunoRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        if(alunoRepository.existsByMatriculaAndData(alunoRequest.getMatricula(), alunoRequest.getData())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Matrícula já existente");
        }

        Aluno aluno = alunoRequest.toModel();

        alunoRepository.save(aluno);

        URI location = uriComponentsBuilder.path("/aluno/{id}")
                .buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> errorConstraintUnique (ConstraintViolationException e, WebRequest request){
        Map<String, Object> body = Map.of(
                "code", 422,
                "error", "unprocessableEntity",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=",""),
                "message","Aluno já cadastrado"
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}

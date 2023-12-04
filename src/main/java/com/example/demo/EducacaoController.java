package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educacao")
public class EducacaoController {

    @Autowired
    private EducacaoRepository educacaoRepository;

    @GetMapping
    public List<Educacao> getAllEducacao() {
        return educacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Educacao> getEducacaoById(@PathVariable Long id) {
        return educacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Educacao> createEducacao(@RequestBody Educacao educacao) {
        Educacao savedEducacao = educacaoRepository.save(educacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEducacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Educacao> updateEducacao(@PathVariable Long id, @RequestBody Educacao educacao) {
        if (!educacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        educacao.setId(id);
        Educacao updatedEducacao = educacaoRepository.save(educacao);
        return ResponseEntity.ok(updatedEducacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducacao(@PathVariable Long id) {
        if (!educacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        educacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

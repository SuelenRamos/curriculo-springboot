package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumo")
public class ResumoController {

    @Autowired
    private ResumoRepository resumoRepository;

    @GetMapping
    public List<Resumo> getAllResumos() {
        return resumoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resumo> getResumoById(@PathVariable Long id) {
        return resumoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resumo> createResumo(@RequestBody Resumo resumo) {
        Resumo savedResumo = resumoRepository.save(resumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResumo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resumo> updateResumo(@PathVariable Long id, @RequestBody Resumo resumo) {
        if (!resumoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        resumo.setId(id);
        Resumo updatedResumo = resumoRepository.save(resumo);
        return ResponseEntity.ok(updatedResumo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResumo(@PathVariable Long id) {
        if (!resumoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        resumoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencia")
public class ExperienciaController {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @GetMapping
    public List<Experiencia> getAllExperiencias() {
        return experienciaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> getExperienciaById(@PathVariable Long id) {
        return experienciaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Experiencia> createExperiencia(@RequestBody Experiencia experiencia) {
        Experiencia savedExperiencia = experienciaRepository.save(experiencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExperiencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experiencia> updateExperiencia(@PathVariable Long id, @RequestBody Experiencia experiencia) {
        if (!experienciaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        experiencia.setId(id);
        Experiencia updatedExperiencia = experienciaRepository.save(experiencia);
        return ResponseEntity.ok(updatedExperiencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperiencia(@PathVariable Long id) {
        if (!experienciaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        experienciaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

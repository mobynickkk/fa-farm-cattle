package ru.fa.io.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fa.io.dto.HerdDto;
import ru.fa.service.domain.CrudService;

@RestController
@RequestMapping("/herd")
@RequiredArgsConstructor
public class HerdController {
    private final CrudService<HerdDto> service;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody HerdDto dto) {
        try {
            return ResponseEntity.ok(service.createOrUpdate(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody HerdDto dto) {
        try {
            return ResponseEntity.ok(service.createOrUpdate(dto.toBuilder().id(id).build()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
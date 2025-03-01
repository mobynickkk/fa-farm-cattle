package ru.fa.io.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.fa.io.dto.AnimalDto;
import ru.fa.service.domain.CrudService;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class HerdController {
    private final CrudService<AnimalDto> service;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(service.getAll(username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(service.getById(username, id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody AnimalDto dto) {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(service.createOrUpdate(dto.toBuilder().username(username).build()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody AnimalDto dto) {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(service.createOrUpdate(dto.toBuilder().id(id).username(username).build()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.slaughter(username, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

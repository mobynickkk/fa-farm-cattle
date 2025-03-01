package ru.fa.io.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.fa.io.dto.CorralDto;
import ru.fa.service.domain.CrudService;

import java.util.UUID;

@RestController
@RequestMapping("/corral")
@RequiredArgsConstructor
public class CorralController {
    private final CrudService<CorralDto> service;

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
    public ResponseEntity<?> create(@RequestBody CorralDto dto) {
        try {
            var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(service.createOrUpdate(dto.toBuilder().id(UUID.randomUUID().toString()).username(username).build()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CorralDto dto) {
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

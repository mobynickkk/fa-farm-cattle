package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.CorralDto;
import ru.fa.persistence.repository.CorralRepository;
import ru.fa.service.mapper.CorralMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CorralService implements CrudService<CorralDto> {
    private final CorralMapper fieldMapper;
    private final CorralRepository fieldRepository;

    public CorralDto createOrUpdate(CorralDto dto) {
        return Optional.of(dto)
                .map(fieldMapper::toEntity)
                .map(fieldRepository::save)
                .map(fieldMapper::toDto)
                .orElseThrow();
    }

    public Collection<CorralDto> getAll() {
        return fieldMapper.toDto(fieldRepository.findAll());
    }

    public CorralDto getById(String id) {
        return fieldRepository.findById(UUID.fromString(id))
                .map(fieldMapper::toDto)
                .orElseThrow();
    }

    public void deleteById(String id) {
        fieldRepository.deleteById(UUID.fromString(id));
    }
}

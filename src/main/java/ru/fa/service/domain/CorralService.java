package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.CorralDto;
import ru.fa.persistence.entity.Corral;
import ru.fa.persistence.repository.CorralRepository;
import ru.fa.service.mapper.CorralMapper;

import java.util.Collection;
import java.util.Objects;
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

    public Collection<CorralDto> getAll(String username) {
        // hibernate путает null и 0, из-за чего int значения надо исключать из поиска явно
        return fieldMapper.toDto(
                fieldRepository.findAll(
                        Example.of(Corral.builder().username(username).build(),
                                ExampleMatcher.matchingAll().withIgnorePaths("capacity"))));
    }

    public CorralDto getById(String username, String id) {
        return fieldRepository.findById(UUID.fromString(id))
                .filter(corral -> Objects.equals(username, corral.getUsername()))
                .map(fieldMapper::toDto)
                .orElseThrow();
    }

    public void slaughter(String username, String id) {
        fieldRepository.deleteById(UUID.fromString(id));
    }
}

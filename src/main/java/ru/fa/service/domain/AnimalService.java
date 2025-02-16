package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.AnimalDto;
import ru.fa.persistence.repository.HerdRepository;
import ru.fa.service.mapper.HerdMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService implements CrudService<AnimalDto> {
    private final HerdMapper sowingMapper;
    private final HerdRepository sowingRepository;

    @Override
    public AnimalDto createOrUpdate(AnimalDto dto) {
        return Optional.of(dto)
                .map(sowingMapper::toEntity)
                .map(sowingRepository::save)
                .map(sowingMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Collection<AnimalDto> getAll() {
        return sowingMapper.toDto(sowingRepository.findAll());
    }

    @Override
    public AnimalDto getById(String id) {
        return sowingRepository.findById(UUID.fromString(id))
                .map(sowingMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        sowingRepository.deleteById(UUID.fromString(id));
    }
}

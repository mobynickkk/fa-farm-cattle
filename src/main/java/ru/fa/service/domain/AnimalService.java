package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.AnimalDto;
import ru.fa.persistence.repository.AnimalRepository;
import ru.fa.service.mapper.AnimalMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService implements CrudService<AnimalDto> {
    private final AnimalMapper cropMapper;
    private final AnimalRepository cropRepository;

    @Override
    public AnimalDto createOrUpdate(AnimalDto dto) {
        return Optional.of(dto)
                .map(cropMapper::toEntity)
                .map(cropRepository::save)
                .map(cropMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Collection<AnimalDto> getAll() {
        return cropMapper.toDto(cropRepository.findAll());
    }

    @Override
    public AnimalDto getById(String id) {
        return cropRepository.findById(UUID.fromString(id))
                .map(cropMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        cropRepository.deleteById(UUID.fromString(id));
    }
}

package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.AnimalCategoryDto;
import ru.fa.persistence.repository.AnimalRepository;
import ru.fa.service.mapper.AnimalMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalCategoryService implements CrudService<AnimalCategoryDto> {
    private final AnimalMapper cropMapper;
    private final AnimalRepository cropRepository;

    @Override
    public AnimalCategoryDto createOrUpdate(AnimalCategoryDto dto) {
        return Optional.of(dto)
                .map(cropMapper::toEntity)
                .map(cropRepository::save)
                .map(cropMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Collection<AnimalCategoryDto> getAll() {
        return cropMapper.toDto(cropRepository.findAll());
    }

    @Override
    public AnimalCategoryDto getById(String id) {
        return cropRepository.findById(UUID.fromString(id))
                .map(cropMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        cropRepository.deleteById(UUID.fromString(id));
    }
}

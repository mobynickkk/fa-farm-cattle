package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.io.dto.HerdDto;
import ru.fa.persistence.repository.HerdRepository;
import ru.fa.service.mapper.HerdMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HerdService implements CrudService<HerdDto> {
    private final HerdMapper sowingMapper;
    private final HerdRepository sowingRepository;

    @Override
    public HerdDto createOrUpdate(HerdDto dto) {
        return Optional.of(dto)
                .map(sowingMapper::toEntity)
                .map(sowingRepository::save)
                .map(sowingMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Collection<HerdDto> getAll() {
        return sowingMapper.toDto(sowingRepository.findAll());
    }

    @Override
    public HerdDto getById(String id) {
        return sowingRepository.findById(UUID.fromString(id))
                .map(sowingMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        sowingRepository.deleteById(UUID.fromString(id));
    }
}

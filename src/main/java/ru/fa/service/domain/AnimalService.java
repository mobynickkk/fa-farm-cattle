package ru.fa.service.domain;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.data.domain.Example;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fa.io.dto.AnimalDto;
import ru.fa.io.dto.CommodityDto;
import ru.fa.io.dto.CorralDto;
import ru.fa.persistence.entity.Animal;
import ru.fa.persistence.entity.AnimalCategory;
import ru.fa.persistence.repository.AnimalRepository;
import ru.fa.persistence.repository.HerdRepository;
import ru.fa.service.mapper.CorralMapper;
import ru.fa.service.mapper.HerdMapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService implements CrudService<AnimalDto> {
    private final HerdMapper animalMapper;
    private final AnimalRepository animalCategoryRepository;
    private final HerdRepository animalRepository;
    private final CorralService corralService;
    private final CorralMapper corralMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public AnimalDto createOrUpdate(AnimalDto dto) {
        var animal = animalMapper.toEntity(dto);
        if(animal.getId() == null) {
            animal.setId(UUID.randomUUID());
        }
        if (animal.getAnimalCategory().getId() == null) {
            var category =
                    animalCategoryRepository.findOne(Example.of(AnimalCategory.builder().name(animal.getAnimalCategory().getName()).build())).orElseThrow();
            animal.setAnimalCategory(category);
        }
        if (animal.getCorral() == null) {
            var corralDto = corralService.getAll(dto.username()).stream().findAny().orElseThrow();
            animal.setCorral(corralMapper.toEntity(corralDto));
        }
        return Optional.of(animal)
                .map(animalRepository::save)
                .map(animalMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Collection<AnimalDto> getAll(String username) {
        return animalMapper.toDto(animalRepository.findAll(Example.of(Animal.builder().username(username).build())));
    }

    @Override
    public AnimalDto getById(String username, String id) {
        return animalRepository.findById(UUID.fromString(id))
                .filter(animal -> Objects.equals(username, animal.getUsername()))
                .map(animalMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void slaughter(String username, String id) {
        var animal = getById(username, id);
        kafkaTemplate.send(new ProducerRecord<>(
                "commodities",
                "meat",
                CommodityDto.of(
                        username,
                        "animal",
                        animal.animalCategory().name(),
                        BigDecimal.TEN,
                        "KILOGRAM")));
        animalRepository.deleteById(UUID.fromString(id));
    }
}

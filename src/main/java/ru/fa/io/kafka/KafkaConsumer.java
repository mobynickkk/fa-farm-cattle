package ru.fa.io.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.fa.io.dto.AnimalDto;
import ru.fa.service.domain.CrudService;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CrudService<AnimalDto> crudService;

    @KafkaListener(
            id = "animal",
            topics = "animal",
            containerFactory = "kafkaListenerContainerFactory",
            properties = {
                    "spring.json.value.default.type=ru.fa.io.dto.AnimalDto"
            }
    )
    public void listen(@Payload AnimalDto animalDto) {
        crudService.createOrUpdate(animalDto);
    }
}

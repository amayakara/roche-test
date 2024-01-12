package com.roche.fizz.buzz.app.service;

import com.roche.fizz.buzz.app.model.StatisticEndpoint;
import com.roche.fizz.buzz.app.repository.StatisticEntity;
import com.roche.fizz.buzz.app.repository.StatisticMapper;
import com.roche.fizz.buzz.app.repository.StatisticRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public StatisticEndpoint findMostCalledEndpoint() {
        return statisticRepository.findMostCalled()
                .map(StatisticMapper::fromEntity)
                .orElse(new StatisticEndpoint());
    }

    public void saveStatisticParameters(StatisticEndpoint statisticEndpoint){
        var incomingStastisticEntity = StatisticMapper.fromModelObject(statisticEndpoint);
        Optional<StatisticEntity> optionalExistingStatisticEntity = statisticRepository.findStatisticEntityByParameters(incomingStastisticEntity.getParameters());
        optionalExistingStatisticEntity.ifPresent(StatisticEntity::increment);
        statisticRepository.save(optionalExistingStatisticEntity.orElse(incomingStastisticEntity));
    }
}

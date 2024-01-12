package com.roche.fizz.buzz.app.repository;

import com.roche.fizz.buzz.app.model.Parameters;
import com.roche.fizz.buzz.app.model.StatisticEndpoint;

public class StatisticMapper {
    public static StatisticEndpoint fromEntity(StatisticEntity statisticEntity){

        var parametersEntity = statisticEntity.getParameters();

        return StatisticEndpoint.builder()
                .parameters(new Parameters(
                        parametersEntity.getInt1(),
                        parametersEntity.getInt2(),
                        parametersEntity.getLimit(),
                        parametersEntity.getStr1(),
                        parametersEntity.getStr2())
                )
                .count(statisticEntity.getCount())
                .build();
    }

    public static StatisticEntity fromModelObject(StatisticEndpoint statisticEndpoint) {
        var parameters = statisticEndpoint.getParameters();
        var parametersEntity = new ParametersEntity(
                parameters.getInt1(),
                parameters.getInt2(),
                parameters.getLimit(),
                parameters.getStr1(),
                parameters.getStr2()
        );

        return new StatisticEntity(0L, statisticEndpoint.getCount(), parametersEntity);
    }
}

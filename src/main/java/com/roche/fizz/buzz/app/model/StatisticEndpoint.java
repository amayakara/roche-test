package com.roche.fizz.buzz.app.model;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class StatisticEndpoint {
    public StatisticEndpoint(Parameters parameters, int count) {
        this.parameters = parameters;
        this.count = count;
    }

    @Embedded
    private Parameters parameters;

    private int count;
}

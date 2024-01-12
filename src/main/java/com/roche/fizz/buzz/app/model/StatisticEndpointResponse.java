package com.roche.fizz.buzz.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticEndpointResponse {
    private StatisticEndpoint mostCalledEndpoint;
}

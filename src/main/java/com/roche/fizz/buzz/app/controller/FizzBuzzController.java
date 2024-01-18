package com.roche.fizz.buzz.app.controller;

import com.roche.fizz.buzz.app.exception.BadRequestException;
import com.roche.fizz.buzz.app.model.*;
import com.roche.fizz.buzz.app.service.FizzBuzzService;
import com.roche.fizz.buzz.app.service.StatisticService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/fizzbuzz")
@Slf4j
@Validated
public class FizzBuzzController {

    @Autowired
    private FizzBuzzService fbService;

    @Autowired
    private StatisticService statisticService;
    @GetMapping("hello")
    public String returnHello(){
        return "hello";
    }

    @GetMapping
    public FizzBuzzResponse generateFbList(
            @RequestParam @Min(value = 1, message = "Value must be greater than one") Integer int1,
            @RequestParam @Min(value = 1, message = "Value must be greater than one") Integer int2,
            @RequestParam @NotNull @Max(Integer.MAX_VALUE) Integer limit,
            @RequestParam @NotBlank String str1,
            @RequestParam @NotBlank String str2) {

        var statisticEndpoint = new StatisticEndpoint(new Parameters(int1, int2, limit, str1, str2), 1);
        statisticService.saveStatisticParameters(statisticEndpoint);
        //validateArguments(int1, int2, str1, str2);
        List<FizzBuzzElement> ls = fbService.generateFizzBuzzList(int1, int2, limit, str1, str2);
        
        return new FizzBuzzResponse(ls.toString());
    }

    @GetMapping("/statistic")
    public StatisticEndpointResponse getMostUsed() {
        StatisticEndpoint mostCalledEndpoint = statisticService.findMostCalledEndpoint();
        return new StatisticEndpointResponse(mostCalledEndpoint);
    }
    private void validateArguments(int int1, int int2, String str1, String str2) {
        if (int1 == 0 || int2 == 0) {
            log.error("[ERROR] int1 = " + int1 + " or int2 = " + int2 + " is null");
            throw new BadRequestException("int1 or int2 shouldn't be equal to zero");
        } if (ObjectUtils.isEmpty(str1) || ObjectUtils.isEmpty(str2)){
            log.error("[ERROR] str1 = " + str1 + " or str2 = " + str2 + " is null");
            throw new BadRequestException("str1 or str2 shouldn't be empty");
        }
    }
}

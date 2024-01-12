package com.roche.fizz.buzz.app.service;

import com.roche.fizz.buzz.app.exception.BadRequestException;
import com.roche.fizz.buzz.app.model.FizzBuzzElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class FizzBuzzServiceImpl implements FizzBuzzService {

    @Override
    public List<FizzBuzzElement> generateFizzBuzzList(Integer int1, Integer int2, Integer limit, String str1, String str2) throws Exception {
        validateArguments(int1, int2, str1, str2);
        return IntStream.rangeClosed(1, limit)
                .mapToObj(v -> checkMultiples(int1, int2, str1, str2, v))
                .collect(Collectors.toList());
    }

    private FizzBuzzElement checkMultiples(Integer int1, Integer int2, String str1, String str2, int i) {
        if(i % (int1 * int2) == 0){
            return new FizzBuzzElement(str1+str2);
        } else if (i % int1 == 0) {
            return new FizzBuzzElement(str1);
        } else if (i % int2 == 0) {
            return new FizzBuzzElement(str2);
        } else {
            return new FizzBuzzElement(i);
        }
    }

    private void validateArguments(int int1, int int2, String str1, String str2) {
        if (int1 == 0 || int2 == 0) {
            log.error("[ERROR] int1 = " + int1 + " or int2 = " + int2 + " is null");
            throw new BadRequestException("int1 or int2 shouldn't be equal to zero");
        }
        if (ObjectUtils.isEmpty(str1) || ObjectUtils.isEmpty(str2)){
            log.error("[ERROR] str1 = " + str1 + " or str2 = " + str2 + " is null");
            throw new BadRequestException("str1 or str2 shouldn't be empty");
        }
    }
}

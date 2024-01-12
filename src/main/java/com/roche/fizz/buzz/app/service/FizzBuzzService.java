package com.roche.fizz.buzz.app.service;

import com.roche.fizz.buzz.app.model.FizzBuzzElement;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FizzBuzzService {

    List<FizzBuzzElement> generateFizzBuzzList(Integer int1, Integer int2, Integer limit, String str1, String str2) throws Exception;
}

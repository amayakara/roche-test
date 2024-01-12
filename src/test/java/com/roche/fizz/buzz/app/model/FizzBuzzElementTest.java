package com.roche.fizz.buzz.app.model;


import com.roche.fizz.buzz.app.exception.InvalidInputParamTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FizzBuzzElementTest {

    @Test
    void creteAnElementFromString() {
        var helloWorld = "helloworld";
        FizzBuzzElement<String> fizzBuzzElement = new FizzBuzzElement<>(helloWorld);
        assertThat(fizzBuzzElement.element()).isEqualTo(helloWorld);
    }

    @Test
    void createAnElemtFromInt() {
        var elementInt = 12;
        FizzBuzzElement<Integer> fizzBuzzElement = new FizzBuzzElement<>(elementInt);
        assertThat(fizzBuzzElement.element()).isEqualTo(elementInt);
    }

    @Test
    void invalidInputParamTypeException() {

        List<Object> sourceValues = List.of(1L, Double.valueOf(156), 'c', true);

        sourceValues.forEach(value -> assertThrows(InvalidInputParamTypeException.class, () -> new FizzBuzzElement<>(value)));
    }
}
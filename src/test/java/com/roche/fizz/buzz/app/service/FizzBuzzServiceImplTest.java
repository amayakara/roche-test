package com.roche.fizz.buzz.app.service;

import com.roche.fizz.buzz.app.exception.BadRequestException;
import com.roche.fizz.buzz.app.model.FizzBuzzElement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzServiceImplTest {

    @MockBean
    FizzBuzzService fizzBuzzService = new FizzBuzzServiceImpl();
    @Test
    public void returnArray() throws Exception {
        List<FizzBuzzElement<?>> elements = new LinkedList();
        elements.add(new FizzBuzzElement<>(1));
        elements.add(new FizzBuzzElement<>(2));
        elements.add(new FizzBuzzElement<>("fizz"));
        elements.add(new FizzBuzzElement<>(4));
        elements.add(new FizzBuzzElement<>("buzz"));
        elements.add(new FizzBuzzElement<>("fizz"));
        elements.add(new FizzBuzzElement<>(7));
        elements.add(new FizzBuzzElement<>(8));
        elements.add(new FizzBuzzElement<>("fizz"));
        elements.add(new FizzBuzzElement<>("buzz"));
        elements.add(new FizzBuzzElement<>(11));
        elements.add(new FizzBuzzElement<>("fizz"));
        elements.add(new FizzBuzzElement<>(13));
        elements.add(new FizzBuzzElement<>(14));
        elements.add(new FizzBuzzElement<>("fizzbuzz"));

        List<FizzBuzzElement> fizzBuzzElements = fizzBuzzService.generateFizzBuzzList(3, 5, 15, "fizz", "buzz");

        assertThat(fizzBuzzElements).hasSameElementsAs(elements);
    }

    @Test
    public void throwExceptionDivisonByZeroInt1() {
        assertThatThrownBy(() -> fizzBuzzService.generateFizzBuzzList(0, 2, 5, "fizz", "buzz"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("int1 or int2 shouldn't be equal to zero");

    }

    @Test
    public void throwExceptionDivisonByZeroInt2() {
        assertThatThrownBy(() -> fizzBuzzService.generateFizzBuzzList(1, 0, 5, "fizz", "buzz"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("int1 or int2 shouldn't be equal to zero");

    }

    @Test
    public void should_return_empty_array_if_limit_is_zero() throws Exception {
        List<FizzBuzzElement> fizzBuzzElements = fizzBuzzService.generateFizzBuzzList(3, 5, 0, "fizz", "buzz");

        assertThat(fizzBuzzElements).isEqualTo(Collections.emptyList());
    }

    @Test
    void returnStringElements() throws Exception {
        List<FizzBuzzElement> fizzBuzzElements = fizzBuzzService.generateFizzBuzzList(3, 5, 16, "fizz", "buzz");
        assertThat(fizzBuzzElements.toString()).isEqualTo("[1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz, 16]");
    }
}

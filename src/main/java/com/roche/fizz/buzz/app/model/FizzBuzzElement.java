package com.roche.fizz.buzz.app.model;

import com.roche.fizz.buzz.app.exception.InvalidInputParamTypeException;

public record FizzBuzzElement<T>(T element) {

    public FizzBuzzElement {
        if(!(element instanceof Integer) && !(element instanceof String))
            throw new InvalidInputParamTypeException("Should be an Integer or String but is : " + element.getClass().getName());
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }
}

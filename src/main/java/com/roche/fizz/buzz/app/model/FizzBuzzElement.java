package com.roche.fizz.buzz.app.model;

public record FizzBuzzElement<T>(T element) {

    @Override
    public String toString() {
        return String.valueOf(element);
    }
}

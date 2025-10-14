package com.juliomesquita.cdc.shared.utils;

public abstract class UseCase<I, O> {
    public abstract O execute(I input);
}

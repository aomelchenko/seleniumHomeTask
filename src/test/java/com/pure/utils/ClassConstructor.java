package com.pure.utils;

import java.util.function.Supplier;

public class ClassConstructor {
    public static <T> T getInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T getInstance(Supplier<T> supplier) {
        return supplier.get();
    }
}

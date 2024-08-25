package com.example.rest.rest.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass//Утильный класс - это final класс, автоматически генерирует конструктор без параметров, все методы static
public class BeanUtils {
    @SneakyThrows //Автоматически обрабатывает исключения
    public void copyNonNullProperties(Object source, Object destination) {
        Class<?> clazz = source.getClass(); //Получаем класс
        Field[] fields = clazz.getDeclaredFields(); //Получаем массив полей

        for (Field field : fields) {
            field.setAccessible(true); //Устанавливаем доступ к полю
            Object value = field.get(source); //Получаем значение поля

            if (value != null) {
                field.set(destination, value); //Устанавливаем значение поля в destination
            }
        }
    }
}

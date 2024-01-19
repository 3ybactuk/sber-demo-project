package com.sber.demo.sberdemoproject.utils.modelmapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилиты для преобразования объектов с помощью ModelMapper.
 */

public class ModelMapperUtils {
    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Преобразует объект из одного класса в другой с использованием ModelMapper.
     *
     * @param source       Исходный объект для преобразования.
     * @param targetClass  Класс, в который нужно преобразовать объект.
     * @param <S>          Класс исходного объекта.
     * @param <T>          Класс целевого объекта.
     * @return Преобразованный объект, или null, если исходный объект равен null.
     */
    public static <S, T> T map(S source, Class<T> targetClass) {
        return source == null ? null : mapper.map(source, targetClass);
    }

    /**
     * Преобразует список объектов из одного класса в список объектов другого класса с использованием ModelMapper.
     *
     * @param source       Список исходных объектов для преобразования.
     * @param targetClass  Класс, в который нужно преобразовать объекты.
     * @param <S>          Класс исходных объектов.
     * @param <T>          Класс целевых объектов.
     * @return Список преобразованных объектов.
     */
    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> map(element, targetClass))
                .collect(Collectors.toList());
    }

}

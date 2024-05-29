package com.rickandmorty.forum.converters;

public interface Mapper<T, U> {
    U map(T t, U u);
}

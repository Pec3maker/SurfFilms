package ru.surfstudio.films_surf.base.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
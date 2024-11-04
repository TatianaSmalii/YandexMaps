package ru.netology.yandexmaps.dto

data class Place( //Модель данных, представляющая информацию о месте.
    val id: Long = 0, //Идентификатор места
    val lat: Double, //Широта
    val long: Double, //Долгота
    val name: String, //Название места
)

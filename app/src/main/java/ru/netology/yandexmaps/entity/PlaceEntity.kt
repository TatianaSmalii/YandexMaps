package ru.netology.yandexmaps.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.yandexmaps.dto.Place

@Entity
data class PlaceEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long, //Идентификатор места
    val latitude: Double, //Широта
    val longitude: Double, //Долгота
    val name: String, //Название места
) {
    companion object {
        fun fromDto(dto: Place): PlaceEntity = with(dto) {
            PlaceEntity(id = id, latitude = lat, longitude = long, name = name)
        }
    }

    fun toDto(): Place = Place(id = id, lat = latitude, long = longitude, name = name)
}

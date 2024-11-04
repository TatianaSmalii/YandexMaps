package ru.netology.yandexmaps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.netology.yandexmaps.db.PlaceDatabase
import ru.netology.yandexmaps.dto.Place
import ru.netology.yandexmaps.entity.PlaceEntity
//Создается класс MapViewModel, который наследуется от AndroidViewModel и принимает параметр context типа Application.
class MapViewModel(context: Application) : AndroidViewModel(context) {
    //объект доступа к данным (DAO) для работы с базой данных PlaceDatabase.
    private val dao = PlaceDatabase.getInstance(context).placeDao
    val places = dao.getAll().map { //Содержит список мест
//Преобразуется из списка объектов PlaceEntity в список объектов PlaceDto, вызывая функцию toDto() на каждом элементе списка.
        it.map(PlaceEntity::toDto)
    }

    fun insertPlace(place: Place) {
        viewModelScope.launch {
            dao.insert(PlaceEntity.fromDto(place))
        }
    }

    fun deletePlaceById(id: Long) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }
}

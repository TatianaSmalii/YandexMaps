package ru.netology.yandexmaps.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.netology.yandexmaps.entity.PlaceEntity

@Dao
interface PlaceDao {
    //Выполняет SQL-запрос для извлечения всех записей из таблицы PlaceEntity.
    @Query("SELECT * FROM PlaceEntity")
    //функция возвращает поток, который будет эмитировать список объектов типа PlaceEntity,
    // полученных из выполнения SQL-запроса.
    fun getAll(): Flow<List<PlaceEntity>>

    //Выполняет SQL-запрос для удаления записи из таблицы PlaceEntity с указанным идентификатором.
    @Query("DELETE FROM PlaceEntity WHERE id = :id")
    //Принимает идентификатор типа Long в качестве параметра, который будет использоваться для удаления
    // соответствующей записи из базы данных.
    suspend fun deleteById(id: Long)

    //Вставляет объект place (типа PlaceEntity) в таблицу базы данных. При конфликте
    // существующих записей, новая запись будет заменять существующую.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //Принимает объект place (типа PlaceEntity) в качестве параметра, который будет вставлен в базу данных.
    suspend fun insert(place: PlaceEntity)
}

package ru.netology.yandexmaps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.yandexmaps.dao.PlaceDao
import ru.netology.yandexmaps.entity.PlaceEntity
//Аннотация указывает, что данный класс является базой данных, содержащей сущности PlaceEntity.
@Database(entities = [PlaceEntity::class], version = 1) //Версия базы данных
abstract class PlaceDatabase : RoomDatabase() {
    abstract val placeDao: PlaceDao
//PlaceDao представляет собой объект доступа к данным (DAO), который предоставляет методы для
    // выполнения операций базы данных, таких как вставка, обновление, удаление и запросы.
    // Этот объект позволяет взаимодействовать с таблицей PlaceEntity в базе данных.

    companion object { //Объект-компаньон для класса PlaceDatabase.
        @Volatile //Переменная синхронизации
        private var INSTANCE: PlaceDatabase? = null
        //Для получения единственного экземпляра базы данных.
        fun getInstance(context: Context): PlaceDatabase =
        //Использует двойную проверку блокировки для обеспечения того, что только один экземпляр
            // базы данных будет создан.
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context, PlaceDatabase::class.java, "place_db")
                    .build()
                    .also {
                        INSTANCE = it //Полученный экземпляр базы данных сохраняется в свойстве
                    }                   // INSTANCE и возвращается из функции.
            }
    }
}

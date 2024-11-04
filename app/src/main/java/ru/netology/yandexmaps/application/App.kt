package ru.netology.yandexmaps.application

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import ru.netology.yandexmaps.BuildConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //Не получается получить доступ к ключу из файла maps.properties! (Этот файл у меня есть).
        MapKitFactory.setApiKey(BuildConfig.MAPS_API_KEY)
        //Если добавить "API_KEY" в скобки, как строку, - тогда работает.
    }
}

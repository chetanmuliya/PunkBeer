package cm.chettas.punkbeer

import android.app.Application
import cm.chettas.punkbeer.utils.SharedPreferencesManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.init(this)
    }
}
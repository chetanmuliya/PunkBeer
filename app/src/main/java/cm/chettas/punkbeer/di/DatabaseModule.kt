package cm.chettas.punkbeer.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import cm.chettas.punkbeer.data.local.BeerLocalDataSource
import cm.chettas.punkbeer.data.local.BeersDatabase
import cm.chettas.punkbeer.data.remote.BeerRemoteDataSource
import cm.chettas.punkbeer.data.remote.BeerService
import cm.chettas.punkbeer.repository.MainRepository
import cm.chettas.punkbeer.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BeersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BeersDatabase::class.java,
            "beers_database.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideLocalDataSource(database: BeersDatabase):
            BeerLocalDataSource = BeerLocalDataSource(database.beerDao())

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: BeerService, local: BeerLocalDataSource):
            BeerRemoteDataSource = BeerRemoteDataSource(service,local)

    @Singleton
    @Provides
    fun provideMainRepository(local: BeerLocalDataSource, remote: BeerRemoteDataSource):
            MainRepository = MainRepositoryImpl(local,remote)

}
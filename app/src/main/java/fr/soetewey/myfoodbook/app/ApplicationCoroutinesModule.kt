package fr.soetewey.myfoodbook.app

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.soetewey.myfoodbook.db.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn( SingletonComponent :: class )
@Module
object ApplicationCoroutinesModule {
    @CoroutineScopeIO
    @Singleton
    @Provides
    fun providesApplicationCoroutineScopeIO () : CoroutineScope {
        return CoroutineScope ( SupervisorJob () + Dispatchers .IO)
    }
}
package fr.soetewey.myfoodbook.db

import javax.inject.Qualifier

@Retention (AnnotationRetention.RUNTIME)
@Qualifier
annotation class CoroutineScopeIO

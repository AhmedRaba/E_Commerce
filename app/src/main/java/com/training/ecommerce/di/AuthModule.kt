package com.training.ecommerce.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.training.ecommerce.data.auth.datasource.AuthDataSource
import com.training.ecommerce.data.auth.datasource.FirebaseAuthDataSource
import com.training.ecommerce.data.auth.repository.AuthRepositoryImpl
import com.training.ecommerce.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {


    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepository {
        return AuthRepositoryImpl(authDataSource)
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): AuthDataSource {
        return FirebaseAuthDataSource(auth, firestore)
    }


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()


}
package com.training.ecommerce.data.auth.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.training.ecommerce.data.model.User
import com.training.ecommerce.data.utils.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthDataSource {

    override suspend fun login(
        email: String,
        password: String,
    ): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                if (!user.isEmailVerified) {
                    return Result.failure(Exception("Email not verified"))
                }
                val userDocument = firestore.collection("users").document(user.uid).get().await()
                if (userDocument.exists()) {
                    Result.success(User(user.uid, user.email ?: ""))
                } else {
                    Result.failure(Exception("No user document found in Firestore"))
                }
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                user.sendEmailVerification().await()

                val userDocument = hashMapOf(
                    "id" to user.uid,
                    "email" to user.email,
                    "name" to name,
                )

                firestore.collection("users").document(user.uid).set(userDocument).await()

                Result.success(
                    User(
                        user.uid, user.email ?: "", name
                    )
                )
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
package com.uabc.amc.cinemareview.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

interface FirestoreFirebase {
    fun updateDataFirebaseFirestore()
}

val FIREBASE_AUTH = Firebase.auth

fun FirestoreCollection(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(collection)
}


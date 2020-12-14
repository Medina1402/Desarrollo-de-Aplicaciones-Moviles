package com.uabc.amc.cinemareview.services

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

interface FirestoreFirebase {
    fun updateDataFirebaseFirestore()
}

fun FirestoreCollection(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(collection)
}
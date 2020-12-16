package com.uabc.amc.cinemareview.services

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.MovieViewPager

interface FirestoreFirebase {
    fun updateDataFirebaseFirestore()
}

fun FirestoreCollection(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(collection)
}
package com.example.bigmarketapp.domain.repository.implementation

import android.util.Log
import com.example.bigmarketapp.domain.model.PagerImageData
import com.example.bigmarketapp.domain.repository.PagerRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class PagerRepositoryImplementation @Inject constructor(
    private val firestore: FirebaseFirestore
) : PagerRepository {
    override val pagerDataList = ArrayList<PagerImageData>()


    private var successLoadListener: (() -> (Unit))? = null

    override fun setSuccessLoadListener(block: () -> Unit) {
        successLoadListener = block
    }

    override fun getPagerImages() {
        firestore.collection("pagerImages")
            .get()
            .addOnSuccessListener { result ->
                for (item in result) {
                    val id = item["id"] as Long
                    val imageURL = item["imageUrl"] as String
                    pagerDataList.add(PagerImageData(id, imageURL))
                }

                successLoadListener?.invoke()
            }
            .addOnFailureListener {

            }
    }


}
package com.ssi.schaefer.lunchbon.acceslogic

import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.Firestore
import com.ssi.schaefer.lunchbon.acceslogic.irepository.IGenericRepository
import com.ssi.schaefer.lunchbon.service.api.model.BaseEntity
import org.springframework.stereotype.Repository

@Repository
class GenericRepository<T: Any> : IGenericRepository<T> {

    private lateinit var collectionName: String
    lateinit var firestore: Firestore
    protected lateinit var documentClass: Class<T>
    private lateinit var collection: CollectionReference

    constructor(firestore: Firestore, collectionName: String, documentClass: Class<T>) {
        this.collectionName = collectionName
        this.firestore = firestore
        this.documentClass = documentClassujik
        this.collection = firestore.collection(collectionName)
    }

    constructor() {

    }

    override fun getById(id: String): T? {
        val document = collection.document(id).get().get()
        return if (document.exists()) {
            val entity = document.toObject(documentClass)
            if (entity is BaseEntity) {
                entity.id = document.id
            }
            entity
        } else {
            null
        }
    }

    override fun getAll(): List<T?> {
        val querySnapshot = collection.get().get()
        return querySnapshot.documents.map { document ->
            val entity = document.toObject(documentClass)
            if (entity is BaseEntity) {
                entity.id = document.id
            }
            entity
        }
    }

    override fun delete(id: String) {
        collection.document(id).delete().get()
    }

    override fun update(id: String, entity: T) {
        collection.document(id).set(entity).get()
    }

    override fun insert(entity: T){
        if (entity is BaseEntity) {
            val documentReference = collection.add(entity).get()
            val generatedId = documentReference.id
            if (entity is BaseEntity) {
                entity.id = generatedId
            }
        } else {
            throw IllegalArgumentException("Entity must be a subtype of BaseEntity")
        }
    }
}
package com.ssi.schaefer.lunchbon.acceslogic.irepository

interface IGenericRepository<T> {
    fun getById(id: String): T?
    fun getAll(): List<T?>
    fun insert(entity: T)
    fun update(id: String, entity: T)
    fun delete(id: String)
}
package com.example.aula8_mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage {
    private val storage = mutableListOf(Operation("1+1", 2.0), Operation("2+3", 5.0))


    companion object{
        private var instance: ListStorage? = null

        fun getInstance(): ListStorage{
            synchronized(this){
                if (instance == null){
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    //suspend
    fun insert(operation: Operation){
        //withContext(Dispatchers.IO){
            //Thread.sleep(1000)
            storage.add(operation)
        //}
    }


    //suspend
    fun getAll() : List<Operation>{
        //withContext(Dispatchers.IO) {Thread.sleep(1000)}
        return storage.toList()
    }

    //suspend
    fun delete(operation: Operation){
        /*withContext(Dispatchers.IO){
            Thread.sleep(1000)
        }*/
            storage.remove(operation)
    }

    fun getLastExpression(): String{
        return storage[storage.size-1].expresssion
    }

}
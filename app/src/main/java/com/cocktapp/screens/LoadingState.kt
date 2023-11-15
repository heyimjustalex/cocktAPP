package com.cocktapp.screens

data class LoadingState (val status:Status, val message:String?=null) {

    companion object{
        val IDLE = LoadingState(Status.IDLE)
        val FAILED = LoadingState(Status.FAILED)
        val LOADING = LoadingState(Status.LOADING)
        val SUCCESS = LoadingState(Status.SUCCESS)
    }

    enum class Status{
        LOADING,
        SUCCESS,
        FAILED,
        IDLE
    }

}

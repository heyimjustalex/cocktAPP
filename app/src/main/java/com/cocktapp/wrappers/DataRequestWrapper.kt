package com.cocktapp.wrappers

class DataRequestWrapper<T,Boolean,E:Exception> (
    var data:T?=null,
    var state: kotlin.String?=null,
    var exception:E?=null

)
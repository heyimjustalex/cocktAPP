package com.cocktapp.model

data class FUser (
    val id:String?,
    val displayName:String


){
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "user_id" to this.id,
            "display_name" to this.displayName

        )
    }
}
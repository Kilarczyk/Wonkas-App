package com.kamil.androidtest.data.model

data class Worker(
    var id: Int = 0,
    var first_name: String? = null,
    var last_name: String? = null,
    var favorite: Favorite = Favorite(),
    var gender: String? = null,
    var image: String? = null,
    var profession: String? = null,
    var email: String? = null,
    var age: Int? = null,
    var country: String? = null,
    var height: Int? = null
)

data class Favorite(
    var color: String? = null,
    var food: String? = null,
    var random_string: String? = null,
    var song: String? = null
)

data class WorkerResponse(
    val results: List<Worker>
)
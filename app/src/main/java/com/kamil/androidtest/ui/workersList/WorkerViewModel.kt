package com.kamil.androidtest.ui.workersList

import androidx.lifecycle.MutableLiveData
import com.kamil.androidtest.base.BaseViewModel
import com.kamil.androidtest.data.model.Worker

class WorkerViewModel: BaseViewModel() {

    private val id = MutableLiveData<Int>()
    private val firstName = MutableLiveData<String>()
    private val lastName = MutableLiveData<String>()
    private val color = MutableLiveData<String>()
    private val food = MutableLiveData<String>()
    private val randomString = MutableLiveData<String>()
    private val song = MutableLiveData<String>()
    private val gender = MutableLiveData<String>()
    private val image = MutableLiveData<String>()
    private val profession = MutableLiveData<String>()
    private val email = MutableLiveData<String>()
    private val age = MutableLiveData<String>()
    private val country = MutableLiveData<String>()
    private val height = MutableLiveData<String>()

    fun bind(worker: Worker) {
        id.value = worker.id
        firstName.value = worker.first_name
        lastName.value = worker.last_name
        color.value = worker.favorite.color
        food.value = worker.favorite.food
        randomString.value = worker.favorite.random_string
        song.value = worker.favorite.song
        gender.value = when(worker.gender){
            "F" -> "Female"
            "M" -> "Male"
            else -> "Unknown"
        }
        image.value = worker.image
        profession.value = worker.profession
        email.value = worker.email
        age.value = worker.age.toString()
        country.value = worker.country
        height.value = worker.height.toString()
    }

    fun getId(): MutableLiveData<Int>{
        return id
    }

    fun getName(): MutableLiveData<String>{
        return firstName
    }

    fun getSurname(): MutableLiveData<String>{
        return lastName
    }

    fun getFavouriteColor(): MutableLiveData<String>{
        return color
    }

    fun getFavouriteFood(): MutableLiveData<String>{
        return food
    }

    fun getRandomString(): MutableLiveData<String>{
        return randomString
    }

    fun getSong(): MutableLiveData<String>{
        return song
    }

    fun getGender(): MutableLiveData<String>{
        return gender
    }

    fun getImage(): MutableLiveData<String> {
        return image
    }

    fun getProfession(): MutableLiveData<String>{
        return profession
    }

    fun getEmail(): MutableLiveData<String>{
        return email
    }

    fun getAge(): MutableLiveData<String>{
        return age
    }

    fun getCountry(): MutableLiveData<String>{
        return country
    }

    fun getHeight(): MutableLiveData<String>{
        return height
    }

}
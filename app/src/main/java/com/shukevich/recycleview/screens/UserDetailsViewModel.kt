package com.shukevich.recycleview.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shukevich.recycleview.UserNotFoundException
import com.shukevich.recycleview.model.UserDetails
import com.shukevich.recycleview.model.UserService


class UserDetailsViewModel(
    private val usersService: UserService
) : ViewModel() {
    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun loadUser(userId: Long) {
        if (_userDetails.value != null) return
        try {
           _userDetails.value = usersService.getById(userId)
        } catch ( e: UserNotFoundException){
            e.printStackTrace()
        }
    }

    fun deleteUser() {
        val userDetails = this.userDetails.value ?: return
        usersService.deleteUser(userDetails.user)
    }
}
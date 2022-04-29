package com.shukevich.recycleview.screens

import com.shukevich.recycleview.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)

}
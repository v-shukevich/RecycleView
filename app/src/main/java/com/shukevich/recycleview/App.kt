package com.shukevich.recycleview

import android.app.Application
import com.shukevich.recycleview.model.UserService

class App: Application() {

    val usersService = UserService()
}
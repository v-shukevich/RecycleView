package com.shukevich.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.shukevich.recycleview.databinding.ActivityMainBinding
import com.shukevich.recycleview.screens.UserListFragment
import com.shukevich.recycleview.model.User
import com.shukevich.recycleview.screens.Navigator
import com.shukevich.recycleview.screens.UserDetailsFragment


class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, UserListFragment())
                .commit()
        }

    }

    override fun showDetails(user: User) {
       supportFragmentManager.beginTransaction()
           .addToBackStack(null)
           .replace(R.id.fragmentContainer, UserDetailsFragment.newInstance(user.id))
           .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRes: Int) {
       Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }

}
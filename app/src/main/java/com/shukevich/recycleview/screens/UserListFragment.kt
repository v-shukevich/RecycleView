package com.shukevich.recycleview.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shukevich.recycleview.UserActionListener
import com.shukevich.recycleview.UsersAdapter
import com.shukevich.recycleview.databinding.FragmentUsersListBinding
import com.shukevich.recycleview.model.User

class UserListFragment : Fragment(){

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: UserListViewModel by viewModels{ factory()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(object : UserActionListener{
            override fun onUserMove(user: User, moveBy: Int) {
                viewModel.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                viewModel.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                navigator().showDetails(user)
            }
        })

        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}
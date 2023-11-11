package com.hadis.todoapplicarion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadis.todoapplicarion.databinding.FragmentAddTaskBinding
import com.hadis.todoapplicarion.databinding.FragmentCurrentTodosBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


lateinit var currentTodosBinding: FragmentCurrentTodosBinding

class CurrentTodos : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentTodosBinding = FragmentCurrentTodosBinding.inflate(inflater, container, false)
        return currentTodosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTodosBinding.addTaskButton.setOnClickListener {
            Navigation.findNavController(currentTodosBinding.addTaskButton)
                .navigate(R.id.action_currentTodo_to_addTask)
        }
        lifecycleScope.launch {
            initRecycleView()
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            initRecycleView()
        }
    }

    private suspend fun initRecycleView() {
        val adaptor = TodoAdaptor(requireContext().dataStore.data.first().todoList.toMutableList(), requireContext())
        currentTodosBinding.recycleView.adapter = adaptor
        currentTodosBinding.recycleView.layoutManager = LinearLayoutManager(requireContext())
    }
}
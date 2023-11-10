package com.hadis.todoapplicarion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.hadis.todoapplicarion.databinding.FragmentAddTaskBinding
import com.hadis.todoapplicarion.databinding.FragmentCurrentTodosBinding

class CurrentTodos : Fragment() {
    private lateinit var binding: FragmentCurrentTodosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      binding.addTaskButton.setOnClickListener{
          Navigation.findNavController(binding.addTaskButton).navigate(R.id.action_currentTodo_to_addTask)
      }
    }
}
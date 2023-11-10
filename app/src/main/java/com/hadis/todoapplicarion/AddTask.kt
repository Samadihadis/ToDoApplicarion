package com.hadis.todoapplicarion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.hadis.todoapplicarion.databinding.FragmentAddTaskBinding

class AddTask : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateTextInput.setOnClickListener {
            Picker(parentFragmentManager , binding.dateTextInput)
        }

        binding.addTaskButton.setOnClickListener{
            val newTodo = Todo(
                binding.addTitleTextView.editText?.text.toString() ,
                binding.addDescriptionTextView.editText?.text.toString(),
                "$hour : $minute",
                fullDate,
                false
            )
            todoList.add(newTodo)
            Navigation.findNavController(binding.addTaskButton).navigate(R.id.action_addTask_to_currentTodo)

        }
    }
}
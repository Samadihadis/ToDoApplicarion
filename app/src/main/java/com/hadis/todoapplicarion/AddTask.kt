package com.hadis.todoapplicarion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            Picker(parentFragmentManager)
        }

        binding.addTaskButton.setOnClickListener{
            val newTodo = Todo(
                binding.addTitleTextView.editText?.text.toString() ,
                binding.addDescriptionTextView.editText?.text.toString(),
                "$hour : $minute",
                fullDate,
                false
            )
            Toast.makeText(requireContext(),newTodo.toString(),Toast.LENGTH_LONG).show()
        }
    }
}
package com.hadis.todoapplicarion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.hadis.todoapplicarion.databinding.FragmentAddTaskBinding
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("mainFile.json" , TodoListSerializer())
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
            lifecycleScope.launch {
                requireContext().dataStore.updateData {
                    it.copy(
                        it.todoList.mutate {
                            it.add(newTodo)
                        }
                    )
                }
                Navigation.findNavController(binding.addTaskButton).navigate(R.id.action_addTask_to_currentTodo)
            }
        }
    }
}
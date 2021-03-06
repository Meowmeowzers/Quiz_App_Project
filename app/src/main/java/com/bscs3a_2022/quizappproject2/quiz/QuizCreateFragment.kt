package com.bscs3a_2022.quizappproject2.quiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.R
import com.bscs3a_2022.quizappproject2.databinding.QuizCreateBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizCreateViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizCreateViewModelFactory

class QuizCreateFragment : Fragment() {

//    companion object {
//        fun newInstance() = QuizCreateFragment()
//    }
    private var _binding: QuizCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizCreateBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizCreateViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizCreateViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.quizCreateViewModel = viewModel

        binding.buttonCreateQuizNext.setOnClickListener {
            val quizName = binding.editTextQuizName.text.toString()
            val quizDescription = binding.editTextQuizDescription.text.toString()
            val subject = binding.editTextSubject.text.toString()
            viewModel.createQuiz(quizName, quizDescription, subject)
            findNavController().navigate(R.id.action_quizCreateFragment_to_quizListFragment)
        }
        binding.buttonClear.setOnClickListener {
            viewModel.clearDb()
        }
        binding.buttonClearProblems.setOnClickListener {
            viewModel.clearProblemsDb()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
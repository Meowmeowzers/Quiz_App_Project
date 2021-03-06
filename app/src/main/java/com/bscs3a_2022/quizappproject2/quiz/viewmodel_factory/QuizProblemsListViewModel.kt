package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizProblemsListViewModel (
    val database: QuizDatabaseDao,
    application: Application,
    id: Long
) : AndroidViewModel(application){

    private var _listofProblems: LiveData<List<Problems>>
    var listofProblems: LiveData<List<Problems>>
    var selectedId: Long = 0L

    init{
        _listofProblems = database.getProblemsOfQuiz(id)
        listofProblems = _listofProblems
    }

    fun createProblem(fromQuiz: Long, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newProblems = Problems(0,fromQuiz,description)
            insert(newProblems)
        }
    }
    private fun insert(problems: Problems) {
        Timber.i("db process")
        database.insertProblem(problems)
    }

    fun clearProblems() {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    private val _navigateToProblemChoicesDetails = MutableLiveData<Long>()
    val navigateToProblemChoicesDetails get() = _navigateToProblemChoicesDetails

    fun onProblemItemClicked(id:Long){
        _navigateToProblemChoicesDetails.value = id
    }
    fun onProblemItemNavigated() {
        _navigateToProblemChoicesDetails.value = null
    }

}

class QuizProblemListViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizProblemsListViewModel::class.java)) {
            return QuizProblemsListViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
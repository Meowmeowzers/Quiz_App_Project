package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_set_problem_choices_table")
data class Choices (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "choice_id")
    val choiceId: Long = 0L,

    @ColumnInfo(name = "from_quiz")
    var fromQuiz: Long,

    @ColumnInfo(name = "from_problem")
    var fromProblem: Long ,

    @ColumnInfo(name = "choice_description")
    var description: String,



)
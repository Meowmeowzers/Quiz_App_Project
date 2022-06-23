package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.*

@Entity(tableName = "quiz_set_problems_table")
data class Problems (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "problem_id")
    val problemId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set")
    var fromQuiz: Long,

    @ColumnInfo(name = "problem_description")
    var description: String
)

data class ProblemsFromQuizSet(
    @Embedded val quizSet: QuizSet,
    @Relation(
        parentColumn = "quiz_set_id",
        entityColumn = "from_quiz_set"
        )
    val problems: List<Problems>
    )

@Entity(tableName = "quiz_set_problems2_table")
data class Problems2 (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "problem_id")
    val problemId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set")
    var fromQuiz: Long,

    @ColumnInfo(name = "problem_description")
    var description: String,

    @ColumnInfo(name = "choice1")
    var choice1: String,
    @ColumnInfo(name = "choice2")
    var choice2: String,
    @ColumnInfo(name = "choice3")
    var choice3: String,
    @ColumnInfo(name = "choice4")
    var choice4: String,

    @ColumnInfo(name = "choice_answer")
    var answer: Int? = 0
)

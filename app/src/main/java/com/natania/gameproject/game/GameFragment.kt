package com.natania.gameproject.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.natania.gameproject.R
import com.natania.gameproject.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>
    )

    private lateinit var viewModel: GameViewModel

    private val questions: MutableList<Question> = mutableListOf(


        Question(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")
        ),
        Question(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
        ),
        Question(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )

        ),
        Question(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
        ),
        Question(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )
        ),
        Question(
            text = "What's the build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        Question(
            text = "Which class do you use to create a vector drawable?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        Question(
            text = "Which one of these is an Android navigation component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        Question(
            text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        Question(
            text = "What do you use to mark a layout for data binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numberQuestion = Math.min((questions.size + 1), 5)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameBinding = DataBindingUtil
            .inflate<FragmentGameBinding>(
                inflater,
                R.layout.fragment_game, container, false
            )
        randomizeQuiz()
        binding.game = this
        binding.btnSubmit.setOnClickListener { view: View ->
            val checkedId = binding.rgGame.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerPosition = 0
                when (checkedId) {
                    R.id.rb_second_answer -> answerPosition = 1
                    R.id.rb_third_answer -> answerPosition = 2
                    R.id.rb_fourth_answer -> answerPosition = 3
                }
                if (answers[answerPosition] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numberQuestion) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(
                            R.id
                                .action_gameFragment_to_endGameFragment
                        )
                    }
                }else{
                    view.findNavController().navigate(R.id
                        .action_gameFragment_to_endGameFragment)
                }
            }
        }
        Log.i("GameFragment", "Called Viewmodel")

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        return binding.root

    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(
                R.string.title_android_trivia_question,
                questionIndex + 1, numberQuestion
            )
    }

    private fun randomizeQuiz() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }
}


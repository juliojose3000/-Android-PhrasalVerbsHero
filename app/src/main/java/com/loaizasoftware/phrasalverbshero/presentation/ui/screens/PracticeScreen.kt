package com.loaizasoftware.phrasalverbshero.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.loaizasoftware.core_ui.composables.ContainerWithAnim
import com.loaizasoftware.core_ui.composables.DotsIndicator
import com.loaizasoftware.core_ui.general.AppBar
import com.loaizasoftware.core_ui.general.LoadingIndicator
import com.loaizasoftware.core_ui.general.PHButton
import com.loaizasoftware.core_ui.theme.Blue40
import com.loaizasoftware.core_ui.theme.Green40
import com.loaizasoftware.core_ui.theme.Pink80
import com.loaizasoftware.core_ui.theme.Red40
import com.loaizasoftware.phrasalverbshero.domain.model.Answer
import com.loaizasoftware.phrasalverbshero.domain.model.Question
import com.loaizasoftware.phrasalverbshero.domain.model.QuestionTypeEnum
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PracticeViewModel
import kotlinx.coroutines.launch

@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel,
    verb: Verb,
    navHostController: NavHostController
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            AppBar(
                title = "Practice of phrasal verbs with ${verb.name}",
                iconAppBar = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                navHostController.navigateUp()
                viewModel.questionsList.clear() // Clear questions when navigating back
                viewModel.selectedAnswers.clear() // Clear selected answers when navigating back
                viewModel.shuffledAnswersList.clear() // Clear shuffled answers when navigating back
            }
        }
    ) { contentPadding ->

        LoadingIndicator(isVisible = viewModel.isLoading.value)

        if (!viewModel.isLoading.value && viewModel.questionsList.isNotEmpty()) {

            ContainerWithAnim {

                SetupUI(
                    contentPadding = contentPadding,
                    questionsList = viewModel.questionsList,
                    viewModel = viewModel
                )

            }

        }

    }

}

@Composable
fun SetupUI(
    contentPadding: PaddingValues,
    questionsList: List<Question>,
    viewModel: PracticeViewModel?
) {

    val pagerState = rememberPagerState(pageCount = {
        questionsList.size
    })

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(contentPadding)
            .fillMaxSize()
    ) {

        Questions(
            pagerState = pagerState,
            questionsList = questionsList,
            shuffledAnswersList = viewModel?.shuffledAnswersList ?: emptyList(),
            selectedAnswers = viewModel?.selectedAnswers,
            updateSelectedAnswer = { questionIndex, answerId ->
                viewModel?.updateSelectedAnswer(questionIndex, answerId)
            }
        )

        BottomButtons(
            pagerState = pagerState,
            questionsList = questionsList,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

    }


}

@Composable
fun Questions(
    pagerState: PagerState,
    questionsList: List<Question>,
    shuffledAnswersList: List<List<Answer>>,
    selectedAnswers: SnapshotStateMap<Int, Long?>?,
    updateSelectedAnswer: (Int, Long?) -> Unit?
) {

    Column {

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .weight(1f) // Make sure pager takes up available space
        ) { currentPage ->

            val question = questionsList[currentPage]
            val shuffledAnswers = shuffledAnswersList[currentPage]

            // Get or initialize the selected answer for the current question
            val selectedAnswerId = selectedAnswers?.get(currentPage)
            val selectedAnswerState = remember { mutableStateOf(selectedAnswerId) }

            // Update the ViewModel when the user selects an answer
            selectedAnswerState.value?.let {
                updateSelectedAnswer(currentPage, it)
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
            ) {

                val questionCardModifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopCenter)

                QuestionCard(
                    question = question,
                    selectedAnswerIdState = selectedAnswerState,
                    questionAnswers = shuffledAnswers,
                    modifier = questionCardModifier
                )

            }

        }

        if (questionsList.size > 1) {

            DotsIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                totalDots = questionsList.size,
                selectedIndex = pagerState.currentPage
            )

        }

    }

}

@Composable
fun QuestionCard(
    question: Question,
    selectedAnswerIdState: MutableState<Long?>,
    questionAnswers: List<Answer>,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = question.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(bottom = 32.dp, top = 16.dp)
                .fillMaxWidth()
        )

        questionAnswers.forEach { answer ->

            val isSelected = selectedAnswerIdState.value == answer.id.toLong()
            val isCorrect = answer.id == question.answer.id

            val backgroundColor =
                when {
                    isSelected && isCorrect -> Green40 //Correct Answer
                    isSelected && !isCorrect -> Red40 //Incorrect Answer
                    else -> Pink80
                }

            PHButton(
                text = answer.text,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleMedium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor,
                    contentColor = Color.Black
                )

            ) {
                selectedAnswerIdState.value = answer.id.toLong()
            }
        }

    }


}

@Composable
fun BottomButtons(
    pagerState: PagerState,
    questionsList: List<Question>,
    modifier: Modifier
) {

    val coroutineScope = rememberCoroutineScope()

    Row(modifier = modifier) {

        val buttonModifier = Modifier
            .weight(1f)
            .fillMaxWidth(0.5f)
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = Blue40, // ✅ Background color
            contentColor = Color.Black    // ✅ Text color
        )

        PHButton(
            text = "Previous",
            modifier = buttonModifier,
            colors = buttonColors,
            enabled = pagerState.currentPage > 0
        ) {
            coroutineScope.launch {
                if (pagerState.currentPage == 0) return@launch
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }

        PHButton(
            text = "Next",
            modifier = buttonModifier,
            colors = buttonColors,
            enabled = pagerState.currentPage < questionsList.lastIndex
        ) {
            coroutineScope.launch {
                if (pagerState.currentPage == questionsList.size - 1) return@launch
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }

        }

    }

}


@Preview
@Composable
fun PracticeScreenPreview() {

    val sampleQuestions = listOf(
        Question(
            type = QuestionTypeEnum.SELECT_DEFINITION,
            title = "What does \"Get about\" mean?",
            answer = Answer(101, "Visit many places. Become known. Walk or visit places."),
            wrongAnswers = listOf(
                Answer(201, "Have a good relationship."),
                Answer(202, "Leave."),
                Answer(203, "Be lazy and unproductive.")
            ),
            imageCard = null
        ),
        Question(
            type = QuestionTypeEnum.SELECT_DEFINITION,
            title = "What does \"Bring about\" mean?",
            answer = Answer(102, "To cause something to happen."),
            wrongAnswers = listOf(
                Answer(204, "To delay an event."),
                Answer(205, "To stop doing something."),
                Answer(206, "To cheer someone up.")
            ),
            imageCard = null
        )
    )

    SetupUI(
        contentPadding = PaddingValues(),
        questionsList = sampleQuestions,
        viewModel = null
    )

}
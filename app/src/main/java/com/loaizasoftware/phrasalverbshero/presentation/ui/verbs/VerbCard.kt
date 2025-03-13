package com.loaizasoftware.phrasalverbshero.presentation.ui.verbs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.Purple80

@Composable
fun VerbCardView(verb: Verb, onClick: (verbId: Long) -> Unit = {}) {

    val cardSize = 100.dp

    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Purple80),
        modifier = Modifier
            .padding(16.dp)
            .width(cardSize)
            .height(cardSize),
        onClick = { onClick(verb.id) }
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = verb.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

        }

    }

}

@Preview
@Composable
fun PreviewVerbCardView() {
    VerbCardView(Verb(1, "Hello World", emptyList()))
}
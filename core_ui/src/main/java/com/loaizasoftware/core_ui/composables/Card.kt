package com.loaizasoftware.core_ui.composables

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.loaizasoftware.core_ui.theme.Purple80


@Composable
fun CardView(text: String, id: Long? = null, paddingBottom: Dp = 16.dp, onClick: (phrasalVerbPart: Any) -> Unit = {}) {

    val cardSize = 100.dp

    val actionParam = id ?: text

    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Purple80),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = paddingBottom)
            .width(cardSize)
            .height(cardSize),
        onClick = { onClick( actionParam ) }
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

        }

    }

}

@Preview
@Composable
fun PreviewCardView() {
    CardView(text = "Hello World", id = 1)
}
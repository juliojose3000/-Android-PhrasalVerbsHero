package com.loaizasoftware.core_ui.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loaizasoftware.core_ui.theme.Pink80

@Composable
fun PHButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Pink80, // ✅ Background color
        contentColor = Color.Black    // ✅ Text color
    ),
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    return Button(
        colors = colors,
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(40.dp), // ✅ Adjust the roundness
        enabled = enabled
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = text,
            textAlign = TextAlign.Center,
            style = textStyle
        )
    }

}

@Preview
@Composable
fun PHButtonPreview() {
    val modifier = Modifier.fillMaxWidth(0.5f)
    PHButton(text = "Practice", modifier = modifier, onClick = {})
}
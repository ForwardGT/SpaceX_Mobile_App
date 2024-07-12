package com.example.spacexmobileapp.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AnnotatedString.Builder.AppendStyledText(
    label: String,
    value: String,
    colorTextValue: Color = Color.Unspecified,
    fontStyleValue: FontStyle = FontStyle.Italic,
    newLineIsNeeded : Boolean = true,
) {

    //label
    pushStyle(SpanStyle(
        fontWeight = FontWeight.SemiBold
    ))
    append("$label ")
    pop()


    //value
    pushStyle(SpanStyle(
        fontStyle = fontStyleValue,
        color = colorTextValue
    ))
    append(value)
    pop()
    if (newLineIsNeeded) appendLine()
}

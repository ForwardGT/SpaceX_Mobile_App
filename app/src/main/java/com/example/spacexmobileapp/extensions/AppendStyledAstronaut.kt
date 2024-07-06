package com.example.spacexmobileapp.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AnnotatedString.Builder.AppendStyledAstronaut(
    label: String,
    value: String
) {
    pushStyle(SpanStyle(fontWeight = FontWeight.SemiBold))
    append("$label ")
    pop()

    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
    append(value)
    pop()
    appendLine()
}

package com.amlavati.todo.data.models


import androidx.compose.ui.graphics.Color
import com.amlavati.todo.ui.theme.HighPriorityColor
import com.amlavati.todo.ui.theme.LowPriorityColor
import com.amlavati.todo.ui.theme.MediumPriorityColor
import com.amlavati.todo.ui.theme.NonePriorityColor


enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
package com.example.firstcompose

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)
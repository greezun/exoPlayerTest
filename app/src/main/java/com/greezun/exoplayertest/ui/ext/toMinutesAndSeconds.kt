package com.greezun.exoplayertest.ui.ext

import java.util.Locale

fun Int.toMinutesAndSeconds(): String {
    val minutes = this / 60
    val seconds = this % 60
    return String.format(locale = Locale.UK, "%d:%02d", minutes, seconds)
}
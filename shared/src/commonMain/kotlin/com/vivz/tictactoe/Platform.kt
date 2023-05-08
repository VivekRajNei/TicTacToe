package com.vivz.tictactoe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
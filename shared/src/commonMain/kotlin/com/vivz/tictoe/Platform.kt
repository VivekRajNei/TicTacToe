package com.vivz.tictoe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
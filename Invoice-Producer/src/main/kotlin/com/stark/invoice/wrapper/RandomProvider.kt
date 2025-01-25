package com.stark.invoice.wrapper

interface RandomProvider {
    fun nextLong(from: Long, until: Long): Long
    fun nextInt(from: Int, until: Int): Int
}

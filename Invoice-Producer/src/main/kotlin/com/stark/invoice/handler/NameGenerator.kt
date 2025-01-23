package com.stark.invoice.handler

interface NameGenerator {
    fun generateNames(count: Int): List<String>
}
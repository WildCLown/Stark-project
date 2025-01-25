package com.stark.invoice.wrapper.impl

import com.stark.invoice.wrapper.RandomProvider
import kotlin.random.Random

class RandomProviderImpl : RandomProvider {
    override fun nextLong(
        from: Long,
        until: Long
    ): Long {
        return Random.nextLong(from, until)
    }

    override fun nextInt(
        from: Int,
        until: Int
    ): Int {
        return Random.nextInt(from, until)
    }
}
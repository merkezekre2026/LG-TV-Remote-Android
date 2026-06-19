package com.lgremote.app.data

/**
 * Encodes LG TV IR hex codes into NEC protocol pulse patterns
 * suitable for Android's [android.hardware.ConsumerIrManager].
 *
 * ## NEC Protocol Timing (microseconds)
 * - Leader: 9000 μs mark, 4500 μs space
 * - Bit 0: 560 μs mark, 560 μs space
 * - Bit 1: 560 μs mark, 1690 μs space
 * - Stop: 560 μs mark
 *
 * ## LG Code Format (4 bytes hex = 32 bits)
 * ```
 * 0x20DF10EF
 *   │  │ │  └── Command complement
 *   │  │ └──── Command
 *   │  └────── Address complement (0xDF = ~0x20)
 *   └───────── Address (always 0x20 for LG TVs)
 * ```
 *
 * ## IMPORTANT: ConsumerIrManager Pattern Units
 * Standard Android expects MICROSECONDS, but many manufacturers (Xiaomi, Huawei, etc.)
 * implemented it with CARRIER CYCLES instead. This encoder supports both.
 * Convert: cycles = (us * freq) / 1_000_000
 */
object NecEncoder {

    // NEC timing constants (microseconds)
    private const val LEADER_MARK_US = 9000
    private const val LEADER_SPACE_US = 4500
    private const val BIT_MARK_US = 560
    private const val ZERO_SPACE_US = 560
    private const val ONE_SPACE_US = 1690
    private const val STOP_MARK_US = 560

    // Bit length of the NEC payload (4 bytes)
    private const val BITS = 32

    /** LG TVs use a 38 kHz carrier. */
    const val CARRIER_FREQUENCY_HZ = 38000

    /** Alternative frequency - some LG models use 37.9 kHz. */
    private const val CARRIER_FREQ_ALT = 37900

    /**
     * Convert microseconds to carrier cycles for this frequency.
     */
    private fun usToCycles(us: Int, freq: Int = CARRIER_FREQUENCY_HZ): Int {
        return ((us.toLong() * freq) / 1_000_000L).toInt().coerceAtLeast(1)
    }

    /**
     * Encode NEC pattern in **microseconds** (standard Android format).
     */
    @JvmStatic
    fun encode(hexCode: String): IntArray {
        return buildPattern(hexCode, LEADER_MARK_US, LEADER_SPACE_US,
            BIT_MARK_US, ZERO_SPACE_US, ONE_SPACE_US, STOP_MARK_US)
    }

    /**
     * Encode NEC pattern in **carrier cycles** (Xiaomi/Huawei format).
     * Many phones require this format instead of microseconds.
     */
    @JvmStatic
    fun encodeCycles(hexCode: String): IntArray {
        val freq = CARRIER_FREQUENCY_HZ
        return buildPattern(hexCode,
            usToCycles(LEADER_MARK_US, freq),
            usToCycles(LEADER_SPACE_US, freq),
            usToCycles(BIT_MARK_US, freq),
            usToCycles(ZERO_SPACE_US, freq),
            usToCycles(ONE_SPACE_US, freq),
            usToCycles(STOP_MARK_US, freq))
    }

    /**
     * Build the pulse pattern with given timing values.
     */
    private fun buildPattern(
        hexCode: String,
        leaderMark: Int, leaderSpace: Int,
        bitMark: Int, zeroSpace: Int, oneSpace: Int,
        stopMark: Int
    ): IntArray {
        require(hexCode.length == 8) { "Hex code must be exactly 8 characters, got: $hexCode" }
        val value = hexCode.toLong(16)

        val pattern = IntArray(2 + 2 * BITS + 1)

        // Leader
        pattern[0] = leaderMark
        pattern[1] = leaderSpace

        // NEC protocol: bytes MSB first (address → ~address → command → ~command),
        // within each byte, bits sent LSB first.
        var idx = 2
        for (bytePos in 3 downTo 0) {
            for (bitPos in 0..7) {
                val bit = ((value shr (bytePos * 8 + bitPos)) and 1L) != 0L
                pattern[idx] = bitMark
                pattern[idx + 1] = if (bit) oneSpace else zeroSpace
                idx += 2
            }
        }

        // Stop bit
        pattern[pattern.lastIndex] = stopMark

        return pattern
    }

    @JvmStatic
    fun carrierFrequency(): Int = CARRIER_FREQUENCY_HZ
}

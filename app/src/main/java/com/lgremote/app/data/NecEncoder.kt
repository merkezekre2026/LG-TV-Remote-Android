package com.lgremote.app.data

import kotlin.math.ceil

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
 * Transmission order: LSB first for each byte, bytes in order address → ~address → command → ~command.
 */
object NecEncoder {

    // NEC timing constants (microseconds)
    private const val LEADER_MARK = 9000
    private const val LEADER_SPACE = 4500
    private const val BIT_MARK = 560
    private const val ZERO_SPACE = 560
    private const val ONE_SPACE = 1690
    private const val STOP_MARK = 560

    // Bit length of the NEC payload (4 bytes)
    private const val BITS = 32

    /**
     * Precompute the carrier frequency to use with transmit().
     * LG TVs use a 38 kHz carrier.
     */
    const val CARRIER_FREQUENCY_HZ = 38000

    /**
     * Convert an LG hex IR code string (e.g. "20DF10EF") into a pulse pattern
     * that can be passed directly to [android.hardware.ConsumerIrManager.transmit].
     *
     * @param hexCode 8-character hex string representing the NEC code
     * @return IntArray of alternating mark (on) / space (off) durations in microseconds.
     *         Length is always 2 (leader) + 2*32 (data bits) + 1 (stop) = 67.
     */
    @JvmStatic
    fun encode(hexCode: String): IntArray {
        require(hexCode.length == 8) { "Hex code must be exactly 8 characters, got: $hexCode" }
        val value = hexCode.toLong(16)

        // 2 for leader + 2 per bit + 1 for stop
        val pattern = IntArray(2 + 2 * BITS + 1)

        // Leader
        pattern[0] = LEADER_MARK
        pattern[1] = LEADER_SPACE

        // NEC protocol: send bytes MSB first (address, ~address, command, ~command),
        // but within each byte, bits are sent LSB first.
        var idx = 2
        for (bytePos in 3 downTo 0) {      // Bytes: MSB → LSB
            for (bitPos in 0..7) {           // Bits within byte: LSB → MSB
                val bit = ((value shr (bytePos * 8 + bitPos)) and 1L) != 0L
                if (bit) {
                    pattern[idx] = BIT_MARK
                    pattern[idx + 1] = ONE_SPACE
                } else {
                    pattern[idx] = BIT_MARK
                    pattern[idx + 1] = ZERO_SPACE
                }
                idx += 2
            }
        }

        // Stop bit
        pattern[pattern.lastIndex] = STOP_MARK

        return pattern
    }

    /**
     * Convenience method: return the LG TV carrier frequency for use with transmit().
     */
    @JvmStatic
    fun carrierFrequency(): Int = CARRIER_FREQUENCY_HZ
}

package com.lgremote.app.data

/**
 * Represents a single LG TV infrared command using NEC protocol.
 *
 * @param name Unique identifier for the command
 * @param hexCode 4-byte NEC hex code in format 20DFXXXX
 * @param displayName Human-readable Turkish display name
 * @param category Button category for UI organization
 */
data class LgIrCode(
    val name: String,
    val hexCode: String,
    val displayName: String,
    val category: Category
) {
    enum class Category {
        POWER,
        NAVIGATION,
        VOLUME,
        CHANNEL,
        NUMPAD,
        MEDIA,
        COLOR,
        SMART_APP,
        INPUT,
        SETTINGS,
        MISC
    }
}

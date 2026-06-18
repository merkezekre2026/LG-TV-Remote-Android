package com.lgremote.app.data

/**
 * Complete database of LG TV infrared remote codes (NEC protocol, 38 kHz carrier).
 *
 * All codes use the NEC format with address byte 0x20 and its complement 0xDF,
 * followed by command byte and its complement.
 *
 * Source: webOS IR key mappings (ledoge/gist), LG TV IR code database.
 *
 * WARNING: Factory/test codes are intentionally excluded to prevent TV configuration damage.
 */
object LgIrDatabase {

    /**
     * NEC protocol carrier frequency in Hz for LG TVs.
     */
    const val CARRIER_FREQUENCY = 38000

    // ───────────── POWER ─────────────

    val powerCodes: List<LgIrCode> = listOf(
        LgIrCode("POWER", "20DF10EF", "Power", LgIrCode.Category.POWER)
    )

    // ───────────── NAVIGATION ─────────────

    val navigationCodes = listOf(
        LgIrCode("UP", "20DF02FD", "Yukarı", LgIrCode.Category.NAVIGATION),
        LgIrCode("DOWN", "20DF827D", "Aşağı", LgIrCode.Category.NAVIGATION),
        LgIrCode("LEFT", "20DFE01F", "Sol", LgIrCode.Category.NAVIGATION),
        LgIrCode("RIGHT", "20DF609F", "Sağ", LgIrCode.Category.NAVIGATION),
        LgIrCode("OK", "20DF22DD", "OK", LgIrCode.Category.NAVIGATION),
        LgIrCode("BACK", "20DF14EB", "Geri", LgIrCode.Category.NAVIGATION),
        LgIrCode("HOME", "20DF3EC1", "Home", LgIrCode.Category.NAVIGATION),
        LgIrCode("EXIT", "20DFDA25", "Exit", LgIrCode.Category.NAVIGATION),
        LgIrCode("MENU", "20DFC23D", "Menü", LgIrCode.Category.NAVIGATION),
        LgIrCode("SEARCH", "20DF1EE1", "Ara", LgIrCode.Category.NAVIGATION),
        LgIrCode("INFO", "20DF55AA", "Info", LgIrCode.Category.NAVIGATION),
        LgIrCode("GUIDE", "20DFD52A", "Rehber", LgIrCode.Category.NAVIGATION),
        LgIrCode("RECENT", "20DFAD52", "Son", LgIrCode.Category.NAVIGATION),
        LgIrCode("INPUT_HUB", "20DF7C83", "Input Hub", LgIrCode.Category.NAVIGATION)
    )

    // ───────────── VOLUME ─────────────

    val volumeCodes = listOf(
        LgIrCode("VOL_UP", "20DF40BF", "Ses +", LgIrCode.Category.VOLUME),
        LgIrCode("VOL_DOWN", "20DFC03F", "Ses -", LgIrCode.Category.VOLUME),
        LgIrCode("MUTE", "20DF906F", "Sessiz", LgIrCode.Category.VOLUME)
    )

    // ───────────── CHANNEL ─────────────

    val channelCodes = listOf(
        LgIrCode("CH_UP", "20DF00FF", "Kanal +", LgIrCode.Category.CHANNEL),
        LgIrCode("CH_DOWN", "20DF807F", "Kanal -", LgIrCode.Category.CHANNEL),
        LgIrCode("FLASHBACK", "20DF58A7", "Önceki Kanal", LgIrCode.Category.CHANNEL),
        LgIrCode("FAVORITE", "20DF7887", "Favori", LgIrCode.Category.CHANNEL),
        LgIrCode("LIVETV", "20DF7986", "Canlı TV", LgIrCode.Category.CHANNEL),
        LgIrCode("PRLIST", "20DFCA35", "Kanal Listesi", LgIrCode.Category.CHANNEL)
    )

    // ───────────── NUMPAD ─────────────

    val numpadCodes = listOf(
        LgIrCode("NUM_1", "20DF8877", "1", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_2", "20DF48B7", "2", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_3", "20DFC837", "3", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_4", "20DF28D7", "4", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_5", "20DFA857", "5", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_6", "20DF6897", "6", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_7", "20DFE817", "7", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_8", "20DF18E7", "8", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_9", "20DF9867", "9", LgIrCode.Category.NUMPAD),
        LgIrCode("NUM_0", "20DF08F7", "0", LgIrCode.Category.NUMPAD),
        LgIrCode("DASH", "20DF32CD", "-", LgIrCode.Category.NUMPAD),
        LgIrCode("ENTER", "20DF22DD", "Enter", LgIrCode.Category.NUMPAD)
    )

    // ───────────── MEDIA ─────────────

    val mediaCodes = listOf(
        LgIrCode("PLAY", "20DF0DF2", "▶", LgIrCode.Category.MEDIA),
        LgIrCode("PAUSE", "20DF5DA2", "⏸", LgIrCode.Category.MEDIA),
        LgIrCode("STOP", "20DF8D72", "⏹", LgIrCode.Category.MEDIA),
        LgIrCode("REWIND", "20DFF10E", "⏪", LgIrCode.Category.MEDIA),
        LgIrCode("FAST_FORWARD", "20DF718E", "⏩", LgIrCode.Category.MEDIA),
        LgIrCode("PREVIOUS", "20DF4DB2", "⏮", LgIrCode.Category.MEDIA),
        LgIrCode("NEXT", "20DFCD32", "⏭", LgIrCode.Category.MEDIA),
        LgIrCode("RECORD", "20DFBD42", "⏺", LgIrCode.Category.MEDIA),
        LgIrCode("RECLIST", "20DF09F6", "Kayıt Listesi", LgIrCode.Category.MEDIA)
    )

    // ───────────── COLORED BUTTONS ─────────────

    val colorCodes = listOf(
        LgIrCode("RED", "20DF4EB1", "Kırmızı", LgIrCode.Category.COLOR),
        LgIrCode("GREEN", "20DF8E71", "Yeşil", LgIrCode.Category.COLOR),
        LgIrCode("YELLOW", "20DFC639", "Sarı", LgIrCode.Category.COLOR),
        LgIrCode("BLUE", "20DF8679", "Mavi", LgIrCode.Category.COLOR)
    )

    // ───────────── SMART APPS ─────────────

    val smartAppCodes = listOf(
        LgIrCode("NETFLIX", "20DF6A95", "Netflix", LgIrCode.Category.SMART_APP),
        LgIrCode("AMAZON", "20DF3AC5", "Prime Video", LgIrCode.Category.SMART_APP),
        LgIrCode("DISNEYPLUS", "20DF8C73", "Disney+", LgIrCode.Category.SMART_APP),
        LgIrCode("APPS", "20DF42BD", "Apps", LgIrCode.Category.SMART_APP),
        LgIrCode("LGCHANNELS", "20DF12ED", "LG Channels", LgIrCode.Category.SMART_APP),
        LgIrCode("RAKUTENTV", "20DF926D", "Rakuten TV", LgIrCode.Category.SMART_APP),
        LgIrCode("HULU", "20DFEC13", "Hulu", LgIrCode.Category.SMART_APP),
        LgIrCode("SPORTS", "20DFDD22", "Spor", LgIrCode.Category.SMART_APP)
    )

    // ───────────── INPUT / HDMI ─────────────

    val inputCodes = listOf(
        LgIrCode("INPUT", "20DFD02F", "Input", LgIrCode.Category.INPUT),
        LgIrCode("HDMI1", "20DF738C", "HDMI 1", LgIrCode.Category.INPUT),
        LgIrCode("HDMI2", "20DF33CC", "HDMI 2", LgIrCode.Category.INPUT),
        LgIrCode("HDMI3", "20DF9768", "HDMI 3", LgIrCode.Category.INPUT),
        LgIrCode("HDMI4", "20DF5BA4", "HDMI 4", LgIrCode.Category.INPUT),
        LgIrCode("TV_INPUT", "20DFF00F", "TV", LgIrCode.Category.INPUT),
        LgIrCode("TV_RADIO", "20DF0FF0", "TV/Radyo", LgIrCode.Category.INPUT),
        LgIrCode("SIMPLINK", "20DF7E81", "Simplink", LgIrCode.Category.INPUT)
    )

    // ───────────── SETTINGS ─────────────

    val settingsCodes = listOf(
        LgIrCode("SETTINGS", "20DFC23D", "Ayarlar", LgIrCode.Category.SETTINGS),
        LgIrCode("QMENU", "20DFA25D", "Hızlı Menü", LgIrCode.Category.SETTINGS),
        LgIrCode("ASPECT", "20DF9E61", "Ekran Oranı", LgIrCode.Category.SETTINGS),
        LgIrCode("SLEEP", "20DF708F", "Uyku Zamanı", LgIrCode.Category.SETTINGS),
        LgIrCode("PICTURE_MODE", "20DFB24D", "Resim Modu", LgIrCode.Category.SETTINGS),
        LgIrCode("SOUND_MODE", "20DF4AB5", "Ses Modu", LgIrCode.Category.SETTINGS),
        LgIrCode("GAME_MODE", "20DF0CF3", "Oyun Modu", LgIrCode.Category.SETTINGS),
        LgIrCode("AUDIO_DESC", "20DF8976", "Sesli Betimleme", LgIrCode.Category.SETTINGS)
    )

    // ───────────── MISC ─────────────

    val miscCodes = listOf(
        LgIrCode("LIVE_ZOOM", "20DFF50A", "Canlı Zoom", LgIrCode.Category.MISC),
        LgIrCode("3D_MODE", "20DF3BC4", "3D Mod", LgIrCode.Category.MISC),
        LgIrCode("SCREEN_REMOTE", "20DF6996", "Ekran Remote", LgIrCode.Category.MISC),
        LgIrCode("TELETEXT", "20DF04FB", "Teletekst", LgIrCode.Category.MISC),
        LgIrCode("STB_POWER", "20DFFC03", "STB Power", LgIrCode.Category.MISC),
        LgIrCode("HELP", "20DF5EA1", "Yardım", LgIrCode.Category.MISC),
        LgIrCode("PAIRING", "20DF41BE", "Eşleştir", LgIrCode.Category.MISC)
    )

    /**
     * All available IR codes organized by category.
     */
    val allCodes: List<LgIrCode> = powerCodes + navigationCodes + volumeCodes +
            channelCodes + numpadCodes + mediaCodes + colorCodes +
            smartAppCodes + inputCodes + settingsCodes + miscCodes

    // ───────────── LOOKUP ─────────────

    /**
     * Find an IR code by its unique name.
     */
    fun findByName(name: String): LgIrCode? = allCodes.find { it.name == name }

    /**
     * Return all codes belonging to a specific category.
     */
    fun byCategory(category: LgIrCode.Category): List<LgIrCode> =
        allCodes.filter { it.category == category }
}

# LG Remote App

Android telefonunuzun IR (kızılötesi) vericisini kullanarak **LG TV**'nizi kontrol eden native Android uygulaması.

## Özellikler

- 🎯 **Tam kumanda**: Power, D-pad + OK, ses +/-, kanal +/-, mute
- 🔢 **NumPad**: 0-9 tuş takımı, önceki kanal, kanal listesi
- ▶️ **Medya kontrolü**: Play, Pause, Stop, Rewind, FF, Previous, Next, Record
- 📱 **Smart TV**: Netflix, Prime Video, Disney+, Apps, LG Channels
- 🔌 **HDMI girişleri**: HDMI 1-4, Input, Canlı TV
- 🎨 **Renkli butonlar**: Kırmızı, Yeşil, Sarı, Mavi
- ⚙️ **Ayarlar**: Ses/Resim modu, Oyun modu, Uyku zamanlayıcı, Ekran oranı
- 📡 **70+ IR komutu**: LG webOS TV'lerin tüm NEC IR kodları

## Gereksinimler

- **IR Blaster** donanımı olan Android telefon (Xiaomi, Poco, Honor, Huawei, TCL, bazı Vivo/Realme modelleri)
- Android 6.0 (API 23) veya üstü
- Samsung Galaxy modellerinde IR blaster bulunmaz (2023+)

## Kurulum

### Android Studio ile (Önerilen)

```bash
# 1. Projeyi Android Studio'da açın
File → Open → LG-Remote-App klasörünü seçin

# 2. İkonları oluşturun
res/mipmap klasörüne sağ tık → New → Image Asset
Launcher Icons (Adaptive and Legacy) oluşturun

# 3. Build alın ve çalıştırın
Run → Run 'app'
```

### Komut satırı ile

```bash
cd LG-Remote-App
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

## Teknik Detaylar

| Bileşen | Teknoloji |
|---|---|
| Dil | Kotlin 2.0 |
| UI | Jetpack Compose + Material 3 |
| IR API | `android.hardware.ConsumerIrManager` |
| Protokol | NEC (38 kHz carrier) |
| Mimari | MVVM (ViewModel + StateFlow) |
| Min SDK | 23 (Android 6.0) |
| Target SDK | 35 |

## Proje Yapısı

```
app/src/main/java/com/lgremote/app/
├── MainActivity.kt          # Ana aktivite + alt navigasyon
├── data/
│   ├── LgIrCode.kt          # IR kod veri modeli
│   ├── LgIrDatabase.kt      # 70+ LG TV IR komutu
│   └── NecEncoder.kt        # NEC protokol sinyal kodlayıcı
├── service/
│   └── IrService.kt         # ConsumerIrManager sarmalayıcı
├── viewmodel/
│   └── RemoteViewModel.kt   # UI durum yönetimi
└── ui/
    ├── theme/Theme.kt       # Material 3 koyu tema (LG kırmızısı)
    ├── navigation/RemoteTab.kt
    └── screens/
        ├── SharedComponents.kt  # Ortak buton bileşenleri
        ├── MainRemoteScreen.kt  # Ana kumanda
        ├── NumpadScreen.kt      # NumPad
        ├── MediaScreen.kt       # Medya + renkli butonlar
        └── SmartScreen.kt       # Smart TV + HDMI + ayarlar
```

## LG TV IR Kodu Referansı

Tüm kodlar NEC protokolünü ve 38 kHz taşıyıcı frekansını kullanır.
LG TV'ler için adres baytı her zaman `0x20` (tamamlayıcı: `0xDF`) şeklindedir.

Örnek:
- Power: `20DF10EF`
- Ses +: `20DF40BF`
- Netflix: `20DF6A95`

Tam liste: [ledoge/lg_ir.txt](https://gist.github.com/ledoge/ab78723efdaa00e6815e30d1792790fd)

## Not

⚠️ Uygulama, TV ayarlarını bozabilecek fabrika/test kodlarını içermez.
Yalnızca güvenli son kullanıcı komutları tanımlanmıştır.

import java.awt.Robot
import java.awt.event.KeyEvent

class VirtualKeyBoard : Robot() {
    @Throws(IllegalArgumentException::class)
    fun pressKeys(keysCombination: String): VirtualKeyBoard {
        keyPress(KeyEvent.VK_BACK_SPACE)
        for (key in keysCombination) {
            try {
                if (key.isLetter() or key.isDigit()) {
                    if (key.isUpperCase()) {
                        keyPress(KeyEvent.VK_SHIFT)
                        pressKeys(
                            KeyEvent::class.java.getField("VK_$key").getInt(null)
                        )
                        keyRelease(KeyEvent.VK_SHIFT)
                    } else {
                        pressKeys(
                            KeyEvent::class.java.getField("VK_" + key.toUpperCase()).getInt(null)
                        )
                    }
                } else if (key == ':') {
                    keyPress(KeyEvent.VK_SHIFT)
                    keyPress(KeyEvent.VK_SEMICOLON)
                    keyRelease(KeyEvent.VK_SEMICOLON)
                    keyRelease(KeyEvent.VK_SHIFT)
                } else if (key == '/') {
                    pressKeys(KeyEvent.VK_SLASH)
                } else if (key == '\\') {
                    pressKeys(KeyEvent.VK_BACK_SLASH)
                } else if (key == '.') {
                    pressKeys(KeyEvent.VK_PERIOD)
                } else if (key == '-') {
                    pressKeys(KeyEvent.VK_MINUS)
                } else if (key == '_') {
                    keyPress(KeyEvent.VK_SHIFT)
                    pressKeys(KeyEvent.VK_MINUS)
                    keyRelease(KeyEvent.VK_SHIFT)
                } else {
                    throw Exception("Symbol '$key' doesn't supported in VirtualKeyBoard!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return this
    }

    fun pressKeys(keyCode: Int): VirtualKeyBoard {
        keyPress(keyCode)
        keyRelease(keyCode)
        return this
    }
}

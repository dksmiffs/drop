package drop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

class DesktopLauncher {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      val config = LwjglApplicationConfiguration()
      config.title = "Drop"
      config.width = 800
      config.height = 480
      LwjglApplication(Drop(), config)
    }
  }
}


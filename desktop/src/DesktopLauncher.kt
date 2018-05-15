import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import HexGdx

class DesktopLauncher {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      val config = LwjglApplicationConfiguration()
      config.title = "HexGDX"
      config.width = 800
      config.height = 480
      LwjglApplication(HexGdx(), config)
    }
  }
}


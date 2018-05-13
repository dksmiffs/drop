import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import HexGdx

class DesktopLauncher {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      val config = LwjglApplicationConfiguration()
      LwjglApplication(HexGdx(), config)
    }
  }
}


import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import MyGdxThird

class DesktopLauncher {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      val config = LwjglApplicationConfiguration()
      LwjglApplication(MyGdxThird(), config)
    }
  }
}


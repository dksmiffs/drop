package drop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Screen

class MainMenuScreen(val game: Drop) : Screen {

  private var camera: OrthographicCamera

  // initializer block
  init {
    camera = OrthographicCamera()
    camera.setToOrtho(false, 800f, 480f)
  }

  // the following overrides are no-ops, unused in tutorial, but needed in
  //    order to compile a class that implements Screen
  override fun hide() { }
  override fun show() { }
  override fun pause() { }
  override fun resume() { }
  override fun resize(width: Int, height: Int) { }
  override fun dispose() { }

  override fun render(delta: Float) {
    // clear the screen with a dark blue color. The arguments to glClearColor
    //    are the RGB and alpha component in the range [0,1] of the color to
    //    be used to clear the screen.
    Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    // generally good practice to update the camera's matrices once per frame
    camera.update()

    // tell the SpriteBatch to render in the coordinate system specified by the
    //    camera.
    game.batch.setProjectionMatrix(camera.combined)

    game.batch.begin()
    game.font.draw(game.batch, "Welcome to Drop!!! ", 100f, 150f)
    game.font.draw(game.batch, "Tap anywhere to begin!", 100f, 100f)
    game.batch.end()

    if (Gdx.input.isTouched()) {
      game.setScreen(GameScreen(game))
      dispose()
    }
  }
}


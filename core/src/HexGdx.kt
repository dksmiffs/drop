import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

class HexGdx : ApplicationAdapter() {
  private lateinit var dropImage: Texture
  private lateinit var bucketImage: Texture
  private lateinit var dropSound: Sound
  private lateinit var rainMusic: Music
  private lateinit var batch: SpriteBatch
	private lateinit var bucket: Rectangle
  // The camera ensures we can render using our target resolution of 800x480
  //    pixels no matter what the screen resolution is.
  private lateinit var camera: OrthographicCamera

  override fun create() {
    batch = SpriteBatch()

		dropImage = Texture(Gdx.files.internal("droplet.png"))
		bucketImage = Texture(Gdx.files.internal("bucket.png"))
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"))
    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))

    // start the playback of the background music immediately
//    rainMusic.setLooping(true)
//    rainMusic.play()

    camera = OrthographicCamera()
    camera.setToOrtho(false, 800f, 480f)

    bucket = Rectangle()
		bucket.x = 800f/2f - 64f/2f
		bucket.y = 20f
		bucket.width = 64f
		bucket.height = 64f
  }

  override fun render() {
    // clear the screen & paint it red
    Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		// generally good practice to update camera once per frame
		camera.update()

		batch.setProjectionMatrix(camera.combined)
    batch.begin()
		batch.draw(bucketImage, bucket.x, bucket.y)
    batch.end()
  }

  override fun dispose() {
    batch.dispose()
  }

}


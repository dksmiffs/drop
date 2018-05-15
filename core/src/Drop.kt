import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils

class Drop : Game() {
  private lateinit var dropImage: Texture
  private lateinit var bucketImage: Texture
  private lateinit var dropSound: Sound
  private lateinit var rainMusic: Music
  private lateinit var batch: SpriteBatch
  private lateinit var font: BitmapFont
  // The camera ensures we can render using our target resolution of 800x480
  //    pixels no matter what the screen resolution is.
  private lateinit var camera: OrthographicCamera
  private lateinit var bucket: Rectangle
  private lateinit var touchPos: Vector3
  private lateinit var raindrops: Array<Rectangle> // gdx, not Kotlin Array
  private var lastDropTime: Long = 0L

  private fun spawnRaindrop() {
    var raindrop = Rectangle()
    raindrop.x = MathUtils.random(0f, 800f-64f)
    raindrop.y = 480f
    raindrop.width = 64f
    raindrop.height = 64f
    raindrops.add(raindrop)
    lastDropTime = TimeUtils.nanoTime()
  }

  override fun create() {
    // load the images for the droplet & bucket, 64x64 pixels each
    dropImage = Texture(Gdx.files.internal("droplet.png"))
    bucketImage = Texture(Gdx.files.internal("bucket.png"))

    // load the drop sound effect and the rain background music
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"))
    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))

    // start the playback of the background music immediately
    rainMusic.setLooping(true)
    rainMusic.play()

    // create the camera and the SpriteBatch
    camera = OrthographicCamera()
    camera.setToOrtho(false, 800f, 480f)
    batch = SpriteBatch()

    // use LibGDX's default Arial font
    font = BitmapFont()

//    this.setScreen(MainMenuScreen(this))

    // create a Rectangle to logically represent the bucket
    bucket = Rectangle()
    bucket.x = 800f/2f - 64f/2f
    bucket.y = 20f
    bucket.width = 64f
    bucket.height = 64f

    // create the touchPos to store mouse click position
    touchPos = Vector3()

    // create the raindrops array and spawn the first raindrop
    raindrops = Array<Rectangle>()
    spawnRaindrop()
  }

  override fun render() {
    super.render()  // important!

    // clear the screen with a dark blue color. The arguments to glClearColor
    //    are the RGB and alpha component in the range [0,1] of the color to
    //    be used to clear the screen.
    Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    // generally good practice to update the camera's matrices once per frame
    camera.update()

    // tell the SpriteBatch to render in the coordinate system specified by the
    //    camera.
    batch.setProjectionMatrix(camera.combined)

    // begin a new batch and draw the bucket and all drops
    batch.begin()
    batch.draw(bucketImage, bucket.x, bucket.y)
    for (raindrop in raindrops) {
      batch.draw(dropImage, raindrop.x, raindrop.y)
    }
    batch.end()

    // process user input
    if (Gdx.input.isTouched()) {
      touchPos.set(Gdx.input.getX().toFloat(),
                   Gdx.input.getY().toFloat(),
                   0f)
      camera.unproject(touchPos)
      bucket.x = touchPos.x - 64f/2f
    }
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      // getDeltaTime returns the time passed between the last and the current
      //    frame in seconds
      bucket.x -= 200 * Gdx.graphics.getDeltaTime()
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      bucket.x += 200 * Gdx.graphics.getDeltaTime()
    }

    // make sure the bucket stays within the screen bounds
    if (bucket.x < 0f) bucket.x = 0f
    if (bucket.x > 800f-64f) bucket.x = 800f-64f

    // check if we need to create a new raindrop
    if (TimeUtils.nanoTime() - lastDropTime > 1_000_000_000L) spawnRaindrop()

    // move the raindrops, remove any that are beneath the bottom edge of the
    //    screen or that hit the bucket.  In the latter case, play back a sound
    //    effect also
    var iter = raindrops.iterator()
    while (iter.hasNext()) {
      var raindrop = iter.next()
      raindrop.y -= 200 * Gdx.graphics.getDeltaTime()
      if (raindrop.y + 64 < 0) iter.remove()

      if (raindrop.overlaps(bucket)) {
        dropSound.play()
        iter.remove()
      }
    }
  }

  override fun dispose() {
    dropImage.dispose()
    bucketImage.dispose()
    dropSound.dispose()
    rainMusic.dispose()
    batch.dispose()
    font.dispose()
  }
}


plugins {
  kotlin("jvm")
}

val mainSet: SourceDirectorySet = java.sourceSets.getByName("main").java
mainSet.srcDir("src/")

dependencies {
  val gdx_version = rootProject.extra.get("gdx_version")
  compile(kotlin("stdlib"))
  compile("com.badlogicgames.gdx:gdx:$gdx_version")
}


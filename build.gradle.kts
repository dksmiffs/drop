plugins {
  kotlin("jvm") version "1.3.11"
}

buildscript {
  extra.set("gdx_version", "1.9.9")
}

allprojects {

  repositories {
    jcenter()
  }

}


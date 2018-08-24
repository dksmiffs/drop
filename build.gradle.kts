plugins {
  kotlin("jvm") version "1.2.61"
}

buildscript {
  extra.set("gdx_version", "1.9.8")
}

allprojects {

  repositories {
    jcenter()
  }

}


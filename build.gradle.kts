plugins {
  kotlin("jvm") version "1.2.60"
}

buildscript {
  extra.set("gdx_version", "1.9.8")
}

allprojects {

  repositories {
    jcenter()
  }

}


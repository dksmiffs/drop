java {
  val mainSourceSet: SourceDirectorySet = sourceSets.getByName("main").java
  mainSourceSet.srcDir("src/")
}


java {
  val mainJavaSourceSet: SourceDirectorySet = sourceSets.getByName("main").java
  mainJavaSourceSet.srcDir("src/")
}


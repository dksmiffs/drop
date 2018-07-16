plugins {
  kotlin("jvm")
}

// The "main" source set is added by the Java plugin, documented here:
//    docs.gradle.org/current/userguide/java_plugin.html#sec:java_source_sets
// java.srcDirs is a property of the source set specifying the source directory
//    containing Java source.
//
// Original Groovy DSL syntax:
//    sourceSets.main.java.srcDirs = [ "src/" ]
val mainSet: SourceDirectorySet = java.sourceSets.getByName("main").java
mainSet.srcDir("src/")


// This guide provided some help for migrating a task from Groovy to Kotlin DSL:
//    github.com/jnizet/gradle-kotlin-dsl-migration-guide#creating-a-task
// "dependsOn" adds a dependency to a Gradle task.  Under docs.gradle.org:
//    current/userguide/more_about_tasks.html#sec:adding_dependencies_to_tasks
// Example of "dependsOn" usage in Kotlin DSL:
//    github.com/gradle/kotlin-dsl-docs/blob/master/build.gradle.kts
// Source that finally provided insight on setting classpath:
//    github.com/JLLeitschuh/pmd-kotlin/blob/master/build.gradle.kts
//
// Original Groovy DSL syntax:
//    task run(dependsOn: classes, type: JavaExec) {
//        main = project.mainClassName
//        classpath = sourceSets.main.runtimeClasspath
//        workingDir = project.assetsDir
//        ignoreExitValue = true
//    }
tasks {
  val run by creating(JavaExec::class) {
    main = "drop.DesktopLauncher"
    classpath = java.sourceSets["main"].runtimeClasspath
    workingDir = File("../core/assets")
    setIgnoreExitValue(true)
  }
}

dependencies {
  val gdx_version = rootProject.extra.get("gdx_version")
  compile(project(":core"))
  compile(kotlin("stdlib"))
  compile("com.badlogicgames.gdx:gdx-backend-lwjgl:$gdx_version")
  compile("com.badlogicgames.gdx:gdx-platform:$gdx_version:natives-desktop")
}


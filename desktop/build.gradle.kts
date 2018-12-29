plugins {
  kotlin("jvm")
}

// sourcesets documented here:
//    https://docs.gradle.org/current/userguide/kotlin_dsl.html
//
// Original Groovy DSL syntax:
//    sourceSets.main.java.srcDirs = [ "src/" ]
sourceSets {
  main {
    java.srcDir("src/")
  }
}

// This guide provided some help for migrating a task from Groovy to Kotlin DSL:
//    github.com/jnizet/gradle-kotlin-dsl-migration-guide#creating-a-task
// "dependsOn" adds a dependency to a Gradle task.  Under docs.gradle.org:
//    current/userguide/more_about_tasks.html#sec:adding_dependencies_to_tasks
// Example of "dependsOn" usage in Kotlin DSL:
//    github.com/gradle/kotlin-dsl-docs/blob/master/build.gradle.kts
// Source that finally provided insight on setting classpath:
//    https://raw.githubusercontent.com/gradle/kotlin-dsl/9d4f2465571b3b8c30a3009951a85824018ca36f/subprojects/integ-tests/src/test/kotlin/org/gradle/kotlin/dsl/integration/ProjectSchemaAccessorsIntegrationTest.kt
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
    classpath = sourceSets["main"].runtimeClasspath
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


// The "main" source set is added by the Java plugin, documented here:
//    docs.gradle.org/current/userguide/java_plugin.html#sec:java_source_sets
// java.srcDirs is a property of the source set specifying the source directory
//    containing Java source.
//
// Proposed alternate Kotlin DSL syntax:
//    java {
//      val mainSet: SourceDirectorySet = sourceSets.getByName("main").java
//      mainSet.srcDir("src/")
//    }
sourceSets.main.java.srcDirs = [ "src/" ]

// From stackoverflow.com/a/21700818 (author Peter Niederwieser):
//    ext is shorthand for project.ext, and is used to define *extra properties*
//    for the project object. (It's also possible to define extra properties for
//    many other objects.) When reading an extra property, the ext. is omitted
//    (e.g. println project.springVersion or println springVersion). The same
//    works from within methods. It does not make sense to declare a method
//    named ext.
// Official Gradle documentation for extra properties (under docs.gradle.org):
//    current/userguide/writing_build_scripts.html#sec:extra_properties
// kotlin-dsl samples which are documented to be "equivalent of the ext
//    properties found in Gradle Script Groovy":
//       github.com/gradle/kotlin-dsl/tree/master/samples/extra-properties
// Official Gradle documentation on a Project:
//    docs.gradle.org/current/javadoc/org/gradle/api/Project.html
// Kotlin DSL alternative documented in the following two locations:
//    github.com/gradle/kotlin-dsl/issues/626
//    github.com/gradle/kotlin-dsl/releases
//      * scroll to "Updates since v0.15.6"
//      * scroll more to "Unified DSL for Gradle/Project and extra properties"
//
// Proposed alternate Kotlin DSL syntax:
//    val mainClassName: String by project = "drop.DesktopLauncher"
//    val assetsDir: File by project = File("../core/assets")
project.ext.mainClassName = "drop.DesktopLauncher"
project.ext.assetsDir = new File("../core/assets");

// This guide provided some help for migrating a task from Groovy to Kotlin DSL:
//    github.com/jnizet/gradle-kotlin-dsl-migration-guide#creating-a-task
// "dependsOn" adds a dependency to a Gradle task.  Under docs.gradle.org:
//    current/userguide/more_about_tasks.html#sec:adding_dependencies_to_tasks
// Example of "dependsOn" usage in Kotlin DSL:
//    github.com/gradle/kotlin-dsl-docs/blob/master/build.gradle.kts
// Don't know what the "classes" task is, but it was working in Gradle Groovy
//
// Proposed alternate Kotlin DSL syntax:
//    task run by tasks.creating(JavaExec::class) {
//      main = project.mainClassName
//      classpath = java.mainSet.runtimeClasspath
//      workingDir = project.assetsDir
//      ignoreExitValue = true
//      dependsOn(classes)
//    }
task run(dependsOn: classes, type: JavaExec) {
    main = project.mainClassName
    classpath = sourceSets.main.runtimeClasspath
    workingDir = project.assetsDir
    ignoreExitValue = true
}


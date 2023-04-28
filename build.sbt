import com.demandbase.core.plugin.ProjectOps.BuildInfoOps

ThisBuild / publish / skip := true

lazy val root = (project in file("."))
  .settings(name := "hackaton-facebook", publish / skip := true)
  .aggregate(
    `hackaton`
  )

lazy val `hackaton` = (project in file("hackaton"))
  .enablePlugins(JavaAppPackaging, DockerPlugin, JavaAgent)
  .settings(libraryDependencies ++= Seq(
    "com.facebook.api" % "facebook-java-api" % "2.0.0",
    "com.facebook.business.sdk" % "facebook-java-business-sdk" % "16.0.2"
  ))
  .enableBuildInfo()


lazy val preConfDeps = "test->test;compile->compile"

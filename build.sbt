lazy val skinnyVersion = "2.6.0"
lazy val h2Version = "1.4.197"

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  name := "skinny-orm-in-play",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.4",
  routesGenerator := InjectedRoutesGenerator,
  transitiveClassifiers in Global := Seq(Artifact.SourceClassifier),
  incOptions := incOptions.value.withNameHashing(true),
  dependencyOverrides ++= Set(
    "org.slf4j"      % "slf4j-api" % "1.7.25"
  ),
  libraryDependencies ++= Seq(
    filters,
    jdbc,
    guice,
    "com.typesafe.play"    %% "play-json-joda"               % "2.6.9",
    "org.skinny-framework" %% "skinny-orm"                   % skinnyVersion, // instead of anorm
    "org.skinny-framework" %% "skinny-http-client"           % skinnyVersion, // instead of ws
    "org.scalikejdbc"      %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.2",
    "org.flywaydb"         %% "flyway-play"                  % "5.0.0",
    "com.h2database"       %  "h2"                           % h2Version
  )
)

lazy val task = (project in file("task")).settings(
  scalaSource in Compile := baseDirectory.value,
  libraryDependencies ++= Seq(
    "org.skinny-framework" %% "skinny-task" % skinnyVersion,
    "org.skinny-framework" %% "skinny-orm"  % skinnyVersion,
    "com.h2database"       %  "h2"          % "1.4.+"
  ),
  mainClass := Some("TaskRunnner")
)

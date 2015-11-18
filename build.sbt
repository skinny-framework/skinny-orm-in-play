lazy val skinnyVersion = "2.0.+"

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  name := "skinny-orm-in-play",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.11.7",
  resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  routesGenerator := InjectedRoutesGenerator,
  transitiveClassifiers in Global := Seq(Artifact.SourceClassifier),
  incOptions := incOptions.value.withNameHashing(true),
  libraryDependencies ++= Seq(
    filters,
    jdbc,
    "org.skinny-framework" %% "skinny-orm"                   % skinnyVersion, // instead of anorm
    "org.skinny-framework" %% "skinny-http-client"           % skinnyVersion, // instead of ws
    "org.scalikejdbc"      %% "scalikejdbc-play-initializer" % "2.4.+",
    "org.flywaydb"         %% "flyway-play"                  % "2.2.0",
    "com.h2database"       %  "h2"                           % "1.4.+"
  )
).settings(scalariformSettings: _*)

lazy val task = (project in file("task")).settings(
  scalaSource in Compile := baseDirectory.value,
  resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  libraryDependencies ++= Seq(
    "org.skinny-framework" %% "skinny-task" % skinnyVersion,
    "org.skinny-framework" %% "skinny-orm"  % skinnyVersion,
    "com.h2database"       %  "h2"          % "1.4.+"
  ),
  mainClass := Some("TaskRunnner")
)


addSbtPlugin("com.typesafe.play" % "sbt-plugin"           % "2.6.17")
addSbtPlugin("org.scalariform"   % "sbt-scalariform"      % "1.8.2")
addSbtPlugin("io.get-coursier"   % "sbt-coursier"         % "1.0.3")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

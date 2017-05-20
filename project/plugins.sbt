addSbtPlugin("com.typesafe.play" % "sbt-plugin"           % "2.5.15")
addSbtPlugin("org.scalariform"   % "sbt-scalariform"      % "1.6.0")
addSbtPlugin("io.get-coursier"   % "sbt-coursier"         % "1.0.0-RC3")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

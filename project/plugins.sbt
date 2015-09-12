resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
addSbtPlugin("com.typesafe.play" % "sbt-plugin"      % "2.4.3")
addSbtPlugin("com.typesafe.sbt"  % "sbt-scalariform" % "1.3.0")
//addSbtPlugin("com.timushev.sbt"  % "sbt-updates"     % "0.1.9")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

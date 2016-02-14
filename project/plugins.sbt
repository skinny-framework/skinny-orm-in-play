resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
addSbtPlugin("com.typesafe.play" % "sbt-plugin"           % "2.4.6")
addSbtPlugin("org.scalariform"   % "sbt-scalariform"      % "1.6.0")
addSbtPlugin("com.timushev.sbt"  % "sbt-updates"          % "0.1.10")
addSbtPlugin("net.virtual-void"  % "sbt-dependency-graph" % "0.8.2")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

name := "DevBlog"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.26",
  "com.google.guava" % "guava" % "14.0",
  "com.github.ddth" % "ddth-commons" % "0.1.0-SNAPSHOT",
  "net.tanesha.recaptcha4j" % "recaptcha4j" % "0.0.7",
  "com.typesafe" %% "play-plugins-redis" % "2.1.1",
  "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
  "com.github.ddth" %% "play-module-plommon" % "0.4.1-SNAPSHOT",
  javaJdbc,
  javaEbean,
  cache,
  filters
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype snapshots repository" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "pk11-scratch" at "http://pk11-scratch.googlecode.com/svn/trunk"

play.Project.playJavaSettings

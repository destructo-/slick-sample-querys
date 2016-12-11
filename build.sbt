name := "slick-sample-querys"
version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.1.0",
  "com.typesafe" % "config" % "1.3.0",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.github.tminglei" %% "slick-pg" % "0.13.0",
  "org.flywaydb" % "flyway-core" % "3.2.1",
  "com.zaxxer" % "HikariCP" % "2.3.7",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "org.postgresql" % "postgresql" % "9.4.1207",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)

Seq(flywaySettings: _*)
flywayUrl :=
    System.getProperty("flyway.url", "jdbc:postgresql://127.0.0.1:5432/")
flywayUser := System.getProperty("flyway.user", "postgres")
flywayPassword := System.getProperty("flyway.password", "123123")
flywaySchemas := Seq("slick_example")
flywayInitOnMigrate := true
flywayInitVersion := "0"
flywayLocations := Seq("filesystem:" + baseDirectory.value + "/src/main/sql")
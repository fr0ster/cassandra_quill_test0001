name := "quill"

version := "1.0"

scalaVersion := "2.12.17"

libraryDependencies ++= {

  Seq(
    "com.typesafe.akka"         %% "akka-actor"             % "2.4.14",
    "com.typesafe.akka"         %% "akka-slf4j"             % "2.4.14",
    "com.datastax.cassandra"    % "cassandra-driver-core"   % "3.1.1",
    "io.getquill"               %% "quill-cassandra"        % "3.4.10",
    "org.slf4j"                 % "slf4j-api"               % "1.7.5",
    "ch.qos.logback"            % "logback-classic"         % "1.0.9",
    "org.scalatest"             % "scalatest_2.11"          % "2.2.1"               % "test",
    "org.scalameta"             %% "munit"                  % "0.7.26"              % "test",
  )
}

resolvers ++= Seq(
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
)

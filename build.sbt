name := "cassandra_quill_test0001"

version := "1.0"

scalaVersion := "3.2.2"

libraryDependencies ++= {

  Seq(
    "com.typesafe.akka"         %% "akka-actor"             % "2.8.0-M5",
    "com.typesafe.akka"         %% "akka-slf4j"             % "2.8.0-M5",
    "com.datastax.cassandra"    % "cassandra-driver-core"   % "3.+",
    // "io.getquill"               %% "quill-cassandra"        % "4.+",
    "io.getquill"               %% "quill-cassandra"        % "4.6.0.1",
    "org.slf4j"                 % "slf4j-api"               % "1.7.5",
    "ch.qos.logback"            % "logback-classic"         % "1.0.9",
    "org.scalatest"             % "scalatest_2.11"          % "2.2.1"               % "test",
    "org.scalameta"             %% "munit"                  % "0.7.26"              % "test",
  )
}

resolvers ++= Seq(
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
)

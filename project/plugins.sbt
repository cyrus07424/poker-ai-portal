resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.19")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.2.2")

// ebean
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.1.0")

// less
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.2")

// digest
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.4")

// heroku
// https://github.com/heroku/sbt-heroku
addSbtPlugin("com.heroku" % "sbt-heroku" % "2.1.2")
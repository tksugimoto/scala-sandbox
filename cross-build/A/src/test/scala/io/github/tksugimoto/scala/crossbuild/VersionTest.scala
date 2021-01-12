package io.github.tksugimoto.scala.crossbuild

import io.github.tksugimoto.scala.Version
import org.scalatest.{MustMatchers, WordSpecLike}

class VersionTest extends WordSpecLike with MustMatchers {
  "Scala version" should {
    "be 2.12.12" in {
      Version.scalaVersion must be("2.12.12")
    }
  }
}

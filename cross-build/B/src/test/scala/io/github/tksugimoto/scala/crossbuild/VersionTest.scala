package io.github.tksugimoto.scala.crossbuild

import io.github.tksugimoto.scala.Version
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class VersionTest extends AnyWordSpec with Matchers {
  "Scala version" should {
    "be 2.13.3" in {
      Version.scalaVersion should be("2.13.3")
    }
  }
}

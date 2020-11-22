package io.github.tksugimoto.scala.map

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScalaMapAddPriorityTest extends AnyWordSpec with Matchers {
  "map の + method" when {
    "keyが重複" should {
      "追加したvalueで上書きされる" in {
        (Map("a" -> 1) + ("a" -> 99)).apply("a") should be(99)
      }
    }
  }

  "map の ++ method" when {
    "keyが重複" should {
      "右側のvalueで上書きされる" in {
        (Map("a" -> 1) ++ Map("a" -> 99)).apply("a") should be(99)

        (Map("a" -> 99) ++ Map("a" -> 1)).apply("a") should be(1)

        (Map("a" -> 1) ++ Map("a" -> 99) ++ Map("a" -> 555))
          .apply("a") should be(555)
      }
    }
  }
}

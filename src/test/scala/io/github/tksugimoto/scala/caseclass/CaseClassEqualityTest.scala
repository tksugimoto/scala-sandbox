package io.github.tksugimoto.scala.caseclass

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CaseClassEqualityTest extends AnyWordSpec with Matchers {
  case class Class1(val1: String)(implicit val val2: Int)
  case class Class2(val1: String)(implicit val2: Int)
  case class Class3(val1: String)(val2: Int)

  "カリー化されたcase class" should {
    "2つ目以降のカッコの値を等価チェックに使わない" in {
      Class1("1")(2) should be(Class1("1")(2))
      Class1("1")(2) should not be (Class1("aaa")(2))
      Class1("1")(2) should be(Class1("1")(999999))

      Class2("1")(2) should be(Class2("1")(2))
      Class2("1")(2) should not be (Class2("aaa")(2))
      Class2("1")(2) should be(Class2("1")(999999))

      Class3("1")(2) should be(Class3("1")(2))
      Class3("1")(2) should not be (Class3("aaa")(2))
      Class3("1")(2) should be(Class3("1")(999999))
    }
  }
}

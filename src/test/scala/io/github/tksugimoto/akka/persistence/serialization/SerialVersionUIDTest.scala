package io.github.tksugimoto.akka.persistence.serialization

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SerialVersionUIDTest() extends AnyWordSpec with Matchers {
  "SerialVersionUID確認" in {
    uidOf(classOf[BusinessException]) should be(-7177893395906121699L)
    uidOf(classOf[HogeException]) should be(-4527675723895936348L)
    uidOf(classOf[FugaException]) should be(7265873533114567903L)
    uidOf(Message1.getClass) should be(-4199323327581738566L)
    uidOf(classOf[Message2]) should be(2300645227313876042L)
  }

  private def uidOf(clazz: Class[_]): Long =
    java.io.ObjectStreamClass.lookup(clazz).getSerialVersionUID
}

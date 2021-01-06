package io.github.tksugimoto.akka.persistence.serialization

sealed trait Message {
  def id: String
  def content: String
}
// ムダがあるのは敢えて

// case object/class は Scala version 変更で互換性がなくなる
@SerialVersionUID(-4199323327581738566L)
case object Message1 extends Message {
  val id: String = "1"
  val content: String = "xxx"
}

// case object/class は Scala version 変更で互換性がなくなる
@SerialVersionUID(2300645227313876042L)
final case class Message2(value: String) extends Message {
  val id: String = "2"
  val content: String = s"value: ${value}"
}

sealed abstract class BusinessException(val message: Message)
    extends RuntimeException(message.id)
class HogeException(override val message: Message)
    extends BusinessException(message)
class FugaException(override val message: Message)
    extends BusinessException(message)

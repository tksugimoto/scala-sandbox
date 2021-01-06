package io.github.tksugimoto.akka.persistence.serialization

import akka.Done
import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import akka.actor.typed.{ActorRef, Behavior}
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}
import org.scalatest.wordspec.AnyWordSpecLike

class CompatibilityTest
    extends ScalaTestWithActorTestKit()
    with AnyWordSpecLike {
  import CompatibilityTest._

  "MyPersistentBehavior" should {
    "eventを永続化できる" in {
      val actor = testKit.spawn(MyPersistentBehavior())
      val probe = testKit.createTestProbe[Done]
      actor ! MyPersistentBehavior.DoSomething(probe.ref)
      probe.expectMessage(Done)
    }
  }

}

object CompatibilityTest {
  object MyPersistentBehavior {
    sealed trait Command
    final case class DoSomething(replyTo: ActorRef[Done]) extends Command

    sealed trait Event extends KryoSerializable
    final case class Event1(id: Int, message: String, cause: BusinessException)
        extends Event

    sealed trait State {
      def handleCommand(command: Command): Effect[Event, State]
      def handleEvent(event: Event): State
    }
    final case class Initial() extends State {
      override def handleCommand(command: Command): Effect[Event, State] =
        command match {
          case DoSomething(replyTo) =>
            println(s"handle: ${DoSomething(replyTo)}")
            val cause = new HogeException(Message2("123"))
            Effect
              .persist(Event1(id = 42, message = "dummy", cause))
              .thenReply(replyTo) { _ =>
                Done
              }
        }

      override def handleEvent(event: Event): State = event match {
        case event: Event1 =>
          println(s"handled: ${event}")
          event.cause.printStackTrace()
          this
      }
    }

    def apply(): Behavior[Command] = {
      EventSourcedBehavior[Command, Event, State](
        persistenceId = PersistenceId.ofUniqueId("abc"),
        emptyState = Initial(),
        commandHandler = (state, command) => state.handleCommand(command),
        eventHandler = (state, event) => state.handleEvent(event),
      )
    }
  }
}

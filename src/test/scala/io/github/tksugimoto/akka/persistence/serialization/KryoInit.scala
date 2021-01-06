package io.github.tksugimoto.akka.persistence.serialization

import com.esotericsoftware.kryo.serializers.JavaSerializer
import io.altoo.akka.serialization.kryo.DefaultKryoInitializer
import io.altoo.akka.serialization.kryo.serializer.scala.ScalaKryo

class KryoInit extends DefaultKryoInitializer {
  override def postInit(kryo: ScalaKryo): Unit = {
    super.postInit(kryo)
    kryo.addDefaultSerializer(classOf[Throwable], new JavaSerializer)
  }
}

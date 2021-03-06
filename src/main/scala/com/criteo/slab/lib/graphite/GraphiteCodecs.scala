package com.criteo.slab.lib.graphite

import java.time.Instant

import com.criteo.slab.core.Codec
import com.criteo.slab.lib.Values.{Latency, Version}
import com.criteo.slab.lib.graphite.GraphiteStore.Repr

import scala.util.Try

/**
  * Codecs for Graphite store
  */
object GraphiteCodecs {

  implicit def latencyCodec = new Codec[Latency, Repr] {
    override def encode(v: Latency): Repr = Map(
      "latency" -> v.underlying
    )

    override def decode(v: Repr): Try[Latency] = Try {
      Latency(v("latency").toLong)
    }
  }

  implicit def version = new Codec[Version, Repr] {
    override def encode(v: Version): Repr = Map(
      "version" -> v.underlying
    )

    override def decode(v: Repr): Try[Version] = Try(
      Version(v("version"))
    )
  }

  implicit def instant = new Codec[Instant, Repr] {
    override def encode(v: Instant): Repr = Map(
      "datetime" -> v.toEpochMilli
    )

    override def decode(v: Repr): Try[Instant] = Try(Instant.ofEpochMilli(v("datetime").toLong))
  }
}

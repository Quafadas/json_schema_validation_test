//> using scala 3.6.1
//> using platform js
//> using dep com.lihaoyi::upickle::4.0.2
//> using dep io.github.jam01::json-schema::0.1.0

import upickle.default._
import io.github.jam01.json_schema.*
import io.github.jam01.json_schema
import upickle.core.Visitor

@main def validate =

  val schema = scala.io.Source.fromFile("schema.json").toString()
  val registry = new MutableRegistry()

  val sch: Schema = json_schema.from(
    ujson.Readable,
    ujson.Readable.fromString(schema),
    registry = registry
  )
  val validator: Visitor[?, OutputUnit] =
    json_schema.validator(sch, registry = registry)
  val result: OutputUnit = ujson.Str("foo").transform(validator)

  println(result.valid)

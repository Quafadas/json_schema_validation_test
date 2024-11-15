//> using scala 3.6.1
//> using dep com.lihaoyi::upickle::4.0.2
//> using dep io.github.jam01::json-schema::0.1.0
//> using dep com.lihaoyi::requests::0.9.0
//> using dep com.lihaoyi::os-lib::0.9.2

import upickle.default._
import io.github.jam01.json_schema.*
import io.github.jam01.json_schema
import upickle.core.Visitor

@main def validate =
  val registry = new MutableRegistry()
  val schema = os.pwd / "v5.21.0.json.json"
  val sch: Schema = json_schema.from(
    ujson.Readable,
    ujson.Readable.fromFile(schema.toIO),
    registry = registry
  )
  val validator: Visitor[?, OutputUnit] =
    json_schema.validator(sch, registry = registry)
  val result: OutputUnit = ujson.Str("foo").transform(validator)

  println(result.valid)

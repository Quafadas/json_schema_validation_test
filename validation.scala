//> using scala 3.6.1
//> using dep com.lihaoyi::upickle::4.0.2
//> using dep io.github.jam01::json-schema::0.1.0
//> using dep com.lihaoyi::requests::0.9.0
import upickle.default._
import io.github.jam01.json_schema.*
import io.github.jam01.json_schema
import upickle.core.Visitor

@main def validate =
  val schema = requests.get(
    "https://github.com/vega/schema/raw/refs/heads/master/vega-lite/v5.21.0.json"
  )
  val sch: Schema = json_schema.from(
    ujson.Readable,
    ujson.Readable.fromString(schema.text())
  )
  val validator: Visitor[?, OutputUnit] = json_schema.validator(sch)
  val result: OutputUnit = ujson.Str("foo").transform(validator)

  println(result.valid)

package com.sksamuel.avro4s

import org.scalatest.{Matchers, WordSpec}
import shapeless.{:+:, CNil}

class NestedCoproducts extends WordSpec with Matchers {

  "SchemaFor" should {

    "work for empty case classes" in {
      """
         SchemaFor[com.sksamuel.avro4s.NestedCoproducts1.Clazz1A]
      """ should compile
    }

    "work for classes with default params" in {
      """
         SchemaFor[com.sksamuel.avro4s.NestedCoproducts2.Clazz2A]
      """ should compile
    }

    "work for coproducts" in {
      """
         SchemaFor[com.sksamuel.avro4s.NestedCoproducts2.Clazz2C]
      """ should compile
    }

    "work for nested coproducts" in {
      """
         SchemaFor[com.sksamuel.avro4s.NestedCoproducts2.Clazz3A]
      """ should compile
    }

    "work for deep nested coproducts" in {
      """
         SchemaFor[com.sksamuel.avro4s.NestedCoproducts2.Clazz4A]
      """ should compile
    }
  }

}

object NestedCoproducts1 {

  case class Clazz1A()
  case class Clazz1B()
  case class Clazz1C()
  case class Clazz1D()

}


object NestedCoproducts2 {

  import NestedCoproducts1._

  case class Clazz2A(clazz1: Clazz1A, s: String = "Hello world")
  case class Clazz2B(clazz1: Clazz1B, s: String = "Hello from Avro4s")
  case class Clazz2C(cop: Clazz1A :+: Clazz1B :+: Clazz1C :+: Clazz1D :+: CNil)

  case class Clazz3A(value: Clazz2A :+: Clazz2B :+: Clazz2C :+: CNil)
  case class Clazz3B(value: Clazz2A :+: Clazz2B :+: Clazz2C :+: Clazz1B :+: CNil)
  case class Clazz3C(value: Clazz2A :+: Clazz2B :+: Clazz2C :+: Clazz1C :+: CNil)

  case class Clazz4A(value: Clazz3A :+: Clazz3B :+: Clazz3C :+: CNil)

}

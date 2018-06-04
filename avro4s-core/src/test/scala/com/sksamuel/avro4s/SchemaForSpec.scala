package com.sksamuel.avro4s

import org.scalatest.{Matchers, WordSpec}

class SchemaForSpec extends WordSpec with Matchers {

  "SchemaFor" should {
    "work for case classes with complete path and no default value" in {
      """
         SchemaFor[com.sksamuel.avro4s.TestModel.Clazz1]()
      """ should compile
    }

    "work for case classes with import and no default value" in {
      """
         import com.sksamuel.avro4s.TestModel._
         SchemaFor[Clazz1]()
      """ should compile
    }

    "work for case classes with import and default value" in {
      """
         import com.sksamuel.avro4s.TestModel._
         SchemaFor[Clazz2]()
      """ should compile
    }

    // Should work, it fails
    "case 01" in {
      """
         SchemaFor[com.sksamuel.avro4s.TestModel.Clazz2]()
      """ should compile
    }

    // Makes case 01 works....
    "case 02" in {
      """
         import com.sksamuel.avro4s.TestModel.Clazz2
         SchemaFor[com.sksamuel.avro4s.TestModel.Clazz2]()
      """ should compile
    }

    // Should work, it fails
    "case 03" in {
      """
         SchemaFor[com.sksamuel.avro4s.TestModel.Clazz3]()
      """ should compile
    }

    // Should work, it fails
    "case 04" in {
      """
         com.sksamuel.avro4s.TestModel.Clazz2
         SchemaFor[com.sksamuel.avro4s.TestModel.Clazz3]()
      """ should compile
    }
  }
}

object TestModel {
  case class Clazz1(str: String)
  case class Clazz2(str: String = "test")
  case class Clazz3(strC: Clazz2)
}
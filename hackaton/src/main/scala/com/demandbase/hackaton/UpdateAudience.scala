package com.demandbase.hackaton

import com.facebook.ads.sdk.{APIContext, AdAccount, CustomAudience}
import com.google.gson.{JsonArray, JsonObject, JsonPrimitive}

import scala.collection.mutable

object UpdateAudience extends App{
  val ACCOUNT_ID = "226242373353116"
  val appId = "6166499073387079"
  val ACCESS_TOKEN = "EABXoZAfLG6kcBAFFERsuviSaWHVd6uEu2tOxx18XjOEiN5YSuONfvzdCihlzyfZCvv9GYZC4xkV5o1HAGp6Orm2defThZCm6TDTwjbBmp33eAjLGwlDZCMMCQWTpsoJIZAEMRZCuhgGvqS1QFj81TptjVZBQTnLPfZAxi31eQXoggIYp8VTGiod3TH4ZB6qzjZAjkMZD"
  val APP_SECRET = null
  val audienceId = "23854154451310109"
  val context = new APIContext(ACCESS_TOKEN, APP_SECRET).enableDebug(true)
  val account = new AdAccount(ACCOUNT_ID, context)

  updateAudience(audienceId)

  def updateAudience(id: String) = {
    val audience = new CustomAudience(id, context).get().execute();

    // Audience payload schema
    val schema = new JsonArray
    schema.add(new JsonPrimitive("EMAIL_SHA256"))
    schema.add(new JsonPrimitive("PHONE_SHA256"))
    // Audience payload data
    val personA = new JsonArray
    personA.add(new JsonPrimitive(sha256("aaa@example.com")))
    personA.add(new JsonPrimitive(sha256("1234567890")))
    val personB = new JsonArray
    personB.add(new JsonPrimitive(sha256("bbb@example.com")))
    personB.add(new JsonPrimitive(sha256("1234567890")))
    val personC = new JsonArray
    personC.add(new JsonPrimitive(sha256("ccc@example.com")))
    personC.add(new JsonPrimitive(sha256("1234567890")))

    val data = new JsonArray
    data.add(personA)
    data.add(personB)
    data.add(personC)

    val payload = new JsonObject()
    payload.add("schema", schema)
    payload.add("data", data)

    audience.createUser.setPayload(payload.toString).execute
  }

  import java.nio.charset.StandardCharsets
  import java.security.MessageDigest

  def sha256(message: String): String = try {
    val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
    val hash: Array[Byte] = digest.digest(message.getBytes(StandardCharsets.UTF_8))
    toHex(hash)
  } catch {
    case e: Exception =>
      null
  }

  def toHex(bytes: Array[Byte]): String = {
    val sb: mutable.StringBuilder = new mutable.StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%1$02x", b))
    }
    sb.toString
  }
}

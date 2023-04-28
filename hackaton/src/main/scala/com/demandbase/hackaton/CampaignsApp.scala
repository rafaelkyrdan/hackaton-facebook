package com.demandbase.hackaton

import com.facebook.ads.sdk.{APIContext, AdAccount}


object CampaignsApp extends App {


  val ACCOUNT_ID = "226242373353116"
  val appId = "773192630911366"
  val ACCESS_TOKEN = "EABXoZAfLG6kcBAFFERsuviSaWHVd6uEu2tOxx18XjOEiN5YSuONfvzdCihlzyfZCvv9GYZC4xkV5o1HAGp6Orm2defThZCm6TDTwjbBmp33eAjLGwlDZCMMCQWTpsoJIZAEMRZCuhgGvqS1QFj81TptjVZBQTnLPfZAxi31eQXoggIYp8VTGiod3TH4ZB6qzjZAjkMZD"
  val APP_SECRET = null
  val audienceId = "23854154451310109"
  val context = new APIContext(ACCESS_TOKEN, APP_SECRET).enableDebug(true)
  val account = new AdAccount(ACCOUNT_ID, context)


  try {
    val campaigns = account.getCampaigns.requestAllFields.execute
    val a = campaigns.iterator()
    while (a.hasNext) {
      val campaign = a.next()
      System.out.println(campaign)
    }
  } catch {
    case e: Exception =>
      e.printStackTrace()
  }
}


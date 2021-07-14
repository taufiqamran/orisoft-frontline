package com.myGatlingTest
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import Module.Login._
import Module.Document._

import scala.concurrent.duration._

class OrisoftTest extends Simulation{

  val httpProtocol = http
    .baseUrl("https://orisoftst.orisoftsaas.com")
    .disableFollowRedirect
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36")

  val loginFeeder = csv("data/EMP8000.csv").queue

  val login = feed(loginFeeder).exec(Login.loginProcess).exec(Login.loadDashboard)

  val document = exec(ESSEMPMyDocument.loadMyDocument,ESSEMPMyDocument.clickCreateButton,ESSEMPMyDocument.submitCreateDocument,ESSEMPMyDocument.logout)


  val scnMyDocumentCreate = scenario("Create Document").exec(login,document)

  setUp(scnMyDocumentCreate.inject(rampUsers(5) during (10 seconds)).protocols(httpProtocol))

}

## Pisano Java Client Samples for SMS and E-Mail campaigns

```java

        HttpResponse<JsonNode> mailSendingResponse = new Builder().type(HttpReqType.POST)
                                                                  .env(Environments.STAGE)
                                                                  .authToken("{YOUR_TOKEN}")
                                                                  .path("/email_campaigns/{CAMPAIGN_ID}/email_sharings")
                                                                  .payload(new Payload("emails", "ertugrul.cetin@pisano.co", "ertugrul.cetin@pisano.co"))
                                                                  .build()
                                                                  .call();
        System.out.println(mailSendingResponse.getBody());


        HttpResponse<JsonNode> smsSendingResponse = new Builder().type(HttpReqType.POST)
                                                                 .env(Environments.STAGE)
                                                                 .authToken("{YOUR_TOKEN}")
                                                                 .path("/sms_campaigns/{CAMPAIGN_ID}/sms_sharings")
                                                                 .payload(new Payload("phone_numbers", "5330000000", "5330000001"))
                                                                 .build()
                                                                 .call();
        System.out.println(smsSendingResponse.getBody());

```
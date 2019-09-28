package org.pisano.client;

import kong.unirest.HttpResponse;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class PisanoClient {
    private String url;
    private String authorizationToken;
    private String nodeId;
    private HttpReqType type;
    private Payload payload;

    private PisanoClient() {
    }

    private PisanoClient(String url, String authorizationToken, String nodeId, HttpReqType type) {
        this.url = url;
        this.authorizationToken = authorizationToken;
        this.nodeId = nodeId;
        this.type = type;
    }

    public HttpResponse<JsonNode> call() {
        Unirest.config()
               .setObjectMapper(new JacksonObjectMapper());

        if (type == HttpReqType.GET) {
            return Unirest.get(url)
                          .connectTimeout(30000)
                          .header("accept", "application/json")
                          .header("Authorization", authorizationToken)
                          .queryString("nodeId", nodeId)
                          .asJson();
        }

        return Unirest.post(url)
                      .connectTimeout(30000)
                      .header("accept", "application/json")
                      .header("Authorization", authorizationToken)
                      .field(payload.getField(), payload.getData())
                      .asJson();
    }

    public static class Builder {

        private String path;
        private String authorizationToken;
        private String nodeId;
        private Payload payload;
        private HttpReqType type = HttpReqType.GET;
        private Environments env = Environments.STAGE;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder authToken(String authorizationToken) {
            this.authorizationToken = String.format("Token token=\"%s\"", authorizationToken);
            return this;
        }

        public Builder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder type(HttpReqType type) {
            this.type = type;
            return this;
        }

        public Builder env(Environments env) {
            this.env = env;
            return this;
        }

        public Builder payload(Payload payload) {
            this.payload = payload;
            return this;
        }

        public PisanoClient build() {
            PisanoClient pisanoClient = new PisanoClient();
            pisanoClient.authorizationToken = this.authorizationToken;
            pisanoClient.nodeId = this.nodeId;
            pisanoClient.type = this.type;
            pisanoClient.payload = this.payload;
            pisanoClient.url = String.format("%s/%s", this.env.toString(), this.path);
            return pisanoClient;
        }
    }

    public static void main(String[] args) {
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
    }
}

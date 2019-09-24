package org.pisano.client;

public enum Environments {
    STAGE("https://api-stage.pisano.co/v1"),
    PROD("https://api.pisano.co/v1");

    String env;
    Environments(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return env;
    }
}

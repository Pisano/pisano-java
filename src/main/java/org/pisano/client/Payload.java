package org.pisano.client;

import java.util.Arrays;
import java.util.List;

public class Payload {
    private String field;
    private List<String> data;

    public Payload(String field, String... data) {
        this.field = field;
        this.data = Arrays.asList(data);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(String... data) {
        this.data = Arrays.asList(data);
    }
}

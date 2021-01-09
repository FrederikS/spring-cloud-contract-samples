package codes.fdk.sample.contract.consumer;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Foo {

    private final String bar;

    @JsonCreator
    public Foo(String bar) {
        this.bar = bar;
    }

    public String getBar() {
        return bar;
    }

}

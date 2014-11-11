package com.onlyteo.sandbox.backend.domain;

public class Foo {

    private String id;
    private String bar;

    public Foo() {
    }

    public Foo(String id, String bar) {
        this.id = id;
        this.bar = bar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}

package com.teoware.sandbox.web;

import javax.inject.Inject;

public class Foo {

    @Inject
    private Bar bar;

    public String foo() {
        return "FOO! " + bar == null ? "Bar is null" : bar.bar();
    }
}

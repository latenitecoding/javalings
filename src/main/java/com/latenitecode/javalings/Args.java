package com.latenitecode.javalings;

/** Javalings CLI tool */
public class Args {

    public static Args parse(String[] args) {
        return new Args();
    }

    private Args() {}

    public boolean empty() {
        return true;
    }
}

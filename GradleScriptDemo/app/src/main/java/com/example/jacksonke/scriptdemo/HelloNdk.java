package com.example.jacksonke.scriptdemo;

/**
 * Created by jacksonke on 2016/3/30.
 */
public class HelloNdk {
    static {
        System.loadLibrary("hello-jni");
    }

    native public String stringFromJNI();
}

package org.miniwicket.examples;

import org.miniwicket.Application;
import org.miniwicket.Page;

/**
 * Example application demonstrating Mini Wicket framework
 */
public class HelloWorldApplication extends Application {
    
    @Override
    public Class<? extends Page> getHomePage() {
        return HelloWorldPage.class;
    }
    
    @Override
    public void init() {
        super.init();
        System.out.println("Hello World Application initialized!");
    }
}
package org.miniwicket.examples;

import org.miniwicket.markup.html.Label;
import org.miniwicket.markup.html.WebPage;
import org.miniwicket.Model;

/**
 * Hello World page demonstrating Mini Wicket concepts
 */
public class HelloWorldPage extends WebPage {
    
    public HelloWorldPage() {
        // Add components demonstrating key Wicket concepts
        add(new Label("title", "Mini Wicket Framework"));
        add(new Label("message", Model.of("Hello World from Mini Wicket!")));
        add(new Label("description", 
            "This demonstrates key Apache Wicket concepts: " +
            "component hierarchy, markup binding, and model system."));
    }
    
    @Override
    protected String getMarkup() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Mini Wicket - Hello World</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 40px; }
                    h1 { color: #2c3e50; }
                    .message { font-size: 18px; color: #27ae60; margin: 20px 0; }
                    .description { color: #7f8c8d; margin: 20px 0; font-style: italic; }
                </style>
            </head>
            <body>
                <h1 wicket:id="title">Title goes here</h1>
                <div class="message" wicket:id="message">Message goes here</div>
                <div class="description" wicket:id="description">Description goes here</div>
                
                <h2>Demonstrated Wicket Concepts:</h2>
                <ul>
                    <li><strong>Component Hierarchy:</strong> Page contains Label components</li>
                    <li><strong>Markup/Logic Separation:</strong> HTML template with wicket:id binding</li>
                    <li><strong>Model System:</strong> Data binding using IModel interface</li>
                    <li><strong>Request Cycle:</strong> RequestCycle manages rendering</li>
                    <li><strong>Component Lifecycle:</strong> onBeforeRender, onRender, onAfterRender</li>
                </ul>
            </body>
            </html>
            """;
    }
}
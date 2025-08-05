package org.miniwicket.examples;

import org.miniwicket.Application;

/**
 * Main class to run the Hello World example
 */
public class Main {
    
    public static void main(String[] args) {
        // Initialize the application
        HelloWorldApplication app = new HelloWorldApplication();
        Application.set(app);
        app.init();
        
        System.out.println("=== Mini Wicket Framework Demo ===");
        System.out.println();
        
        // Process a request and get the HTML output
        String htmlOutput = app.processHomePageRequest();
        
        System.out.println("Generated HTML:");
        System.out.println("================");
        System.out.println(htmlOutput);
        System.out.println();
        
        System.out.println("=== Key Wicket Concepts Demonstrated ===");
        System.out.println("1. Component-Based Architecture: Page -> Label components");
        System.out.println("2. Markup/Logic Separation: HTML template with wicket:id binding");
        System.out.println("3. Model System: IModel interface for data binding");
        System.out.println("4. Request Cycle: RequestCycle manages the rendering process");
        System.out.println("5. Component Hierarchy: MarkupContainer can hold child components");
        System.out.println("6. Component Lifecycle: onBeforeRender -> onRender -> onAfterRender");
        System.out.println();
        
        System.out.println("This mini-framework extracts the core ideas that make Apache Wicket powerful:");
        System.out.println("- Clean separation of concerns");
        System.out.println("- Component reusability");
        System.out.println("- Type-safe programming model");
        System.out.println("- Hierarchical page structure");
        System.out.println("- Model-driven data binding");
    }
}
# Mini Wicket Framework

A proof-of-concept reduced framework that demonstrates the core technical ideas and architectural patterns from Apache Wicket.

## Overview

This project extracts and implements the fundamental concepts that make Apache Wicket a powerful component-based web framework. By creating a minimal implementation, we can clearly see the key innovations that Wicket brought to web development.

## Key Apache Wicket Concepts Demonstrated

### 1. Component-Based Architecture

**Original Wicket Concept**: Everything in Wicket is a component with a hierarchical structure.

```java
// Mini Wicket Implementation
public abstract class Component implements Serializable {
    private final String id;
    private Component parent;
    private IModel<?> model;
    
    // Component lifecycle: onBeforeRender -> onRender -> onAfterRender
    public final void render() {
        if (!isVisible()) return;
        onBeforeRender();
        onRender();
        onAfterRender();
    }
}

public abstract class MarkupContainer extends Component {
    private final Map<String, Component> children = new LinkedHashMap<>();
    
    public MarkupContainer add(Component... components) {
        // Build component hierarchy
    }
}
```

**Why This Matters**: Component-based architecture enables code reusability, encapsulation, and maintainable web applications by treating UI elements as objects with state and behavior.

### 2. Markup/Logic Separation

**Original Wicket Concept**: Clean separation between HTML templates and Java logic using `wicket:id` binding.

```html
<!-- HTML Template -->
<h1 wicket:id="title">Title goes here</h1>
<div wicket:id="message">Message goes here</div>
```

```java
// Java Logic
public class HelloWorldPage extends WebPage {
    public HelloWorldPage() {
        add(new Label("title", "Mini Wicket Framework"));
        add(new Label("message", "Hello World!"));
    }
}
```

**Why This Matters**: Designers can work on HTML templates while developers work on Java logic, without stepping on each other's toes. The markup stays clean and readable.

### 3. Model System for Data Binding

**Original Wicket Concept**: IModel interface provides a consistent way to access and modify data, with support for lazy loading and detachment.

```java
// Mini Wicket Implementation
public interface IModel<T> extends Serializable {
    T getObject();
    void setObject(T object);
    default void detach() { } // For resource cleanup
}

public class Model<T> implements IModel<T> {
    private T object;
    // Simple implementation of the model pattern
}
```

**Why This Matters**: Models provide a layer of abstraction between components and data, enabling lazy loading, automatic refresh, and clean separation of concerns.

### 4. Request Cycle Management

**Original Wicket Concept**: RequestCycle manages the entire lifecycle of processing a web request.

```java
// Mini Wicket Implementation
public class RequestCycle {
    private static final ThreadLocal<RequestCycle> CURRENT = new ThreadLocal<>();
    private Response response;
    
    public static RequestCycle get() {
        return CURRENT.get();
    }
    
    // Manages request processing lifecycle
}
```

**Why This Matters**: Centralized request processing enables consistent handling of security, session management, and component rendering across the entire application.

### 5. Type-Safe Programming Model

**Original Wicket Concept**: Compile-time checking instead of runtime string-based configuration.

```java
// Type-safe component creation and configuration
Label label = new Label("message", Model.of("Hello World"));
label.setVisible(true);
label.setEnabled(false);

// Compiler catches errors at build time, not runtime
```

**Why This Matters**: Reduces runtime errors, enables IDE support with auto-completion and refactoring, and makes code more maintainable.

### 6. Page-Based Application Structure

**Original Wicket Concept**: Pages as top-level containers that manage the entire web page lifecycle.

```java
public abstract class Page extends MarkupContainer {
    private final int pageId;
    
    protected abstract String getMarkup();
    
    public void render() {
        String markup = getMarkup();
        String processedMarkup = processMarkup(markup);
        getResponse().write(processedMarkup);
    }
}
```

**Why This Matters**: Pages provide a natural unit of organization for web applications, with automatic management of state, versioning, and navigation.

## Running the Demo

```bash
cd mini-wicket
mvn clean compile test
mvn exec:java -Dexec.mainClass="org.miniwicket.examples.Main"
```

## Example Output

The demo generates a complete HTML page demonstrating all the concepts:

```html
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
    <span id="title">Mini Wicket Framework</span>
    <span id="message">Hello World from Mini Wicket!</span>
    <span id="description">This demonstrates key Apache Wicket concepts...</span>
</body>
</html>
```

## Technical Innovations Extracted

1. **Component Hierarchy**: Object-oriented approach to web UI development
2. **Automatic Markup Processing**: Framework handles HTML generation and component binding
3. **Stateful Web Applications**: Components maintain state across requests
4. **Model-View Separation**: Clear separation between data (models) and presentation (components)
5. **Lifecycle Management**: Automatic handling of component initialization, rendering, and cleanup
6. **Request Processing Pipeline**: Structured approach to handling web requests
7. **Session Management**: Automatic handling of component state and page versioning

## Comparison to Full Wicket

| Feature | Mini Wicket | Full Apache Wicket |
|---------|-------------|-------------------|
| Component System | ✅ Basic | ✅ Advanced with behaviors, validators |
| Markup Processing | ✅ Simple | ✅ Advanced with inheritance, panels |
| Model System | ✅ Basic | ✅ Advanced with property models, LDMS |
| Request Cycle | ✅ Basic | ✅ Full with listeners, exception handling |
| AJAX Support | ❌ | ✅ Full AJAX integration |
| Form Processing | ❌ | ✅ Advanced form handling |
| Security | ❌ | ✅ CSRF, authorization, validation |
| Internationalization | ❌ | ✅ Full i18n support |

## Lessons Learned

The core value of Apache Wicket lies in:

1. **Object-Oriented Web Development**: Treating web pages as object hierarchies rather than template processors
2. **Component Reusability**: Building complex UIs from reusable, encapsulated components  
3. **Developer Experience**: Type safety, IDE support, and compile-time error checking
4. **Clean Architecture**: Clear separation between markup, logic, and data
5. **Stateful Abstractions**: Hiding the stateless nature of HTTP behind stateful component models

This mini-framework proves that these concepts can be implemented in a relatively simple way, demonstrating the elegance of Wicket's core design patterns.

## Project Structure

```
mini-wicket/
├── src/main/java/org/miniwicket/
│   ├── Component.java              # Base component class
│   ├── MarkupContainer.java        # Container for child components
│   ├── Page.java                   # Top-level page component
│   ├── IModel.java                 # Model interface
│   ├── Model.java                  # Simple model implementation
│   ├── Application.java            # Application lifecycle management
│   ├── RequestCycle.java           # Request processing
│   ├── Response.java               # Response abstraction
│   ├── markup/html/
│   │   ├── Label.java              # Text display component
│   │   └── WebPage.java            # HTML page base class
│   └── examples/
│       ├── HelloWorldApplication.java
│       ├── HelloWorldPage.java
│       └── Main.java
└── src/test/java/org/miniwicket/
    └── ComponentTest.java          # Unit tests
```

This structure mirrors the organization of the full Apache Wicket framework, making it easy to understand how the concepts scale to a full-featured framework.
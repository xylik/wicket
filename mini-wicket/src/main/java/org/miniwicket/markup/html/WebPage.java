package org.miniwicket.markup.html;

import org.miniwicket.IModel;
import org.miniwicket.Page;

/**
 * Base class for HTML pages.
 * Demonstrates page structure with HTML markup support.
 */
public abstract class WebPage extends Page {
    
    public WebPage() {
        super();
    }
    
    public WebPage(IModel<?> model) {
        super();
        setModel(model);
    }
    
    /**
     * Default HTML page structure if no custom markup is provided
     */
    @Override
    protected String getMarkup() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Mini Wicket Page</title>
            </head>
            <body>
                <!-- Page content will be rendered here -->
            </body>
            </html>
            """;
    }
}
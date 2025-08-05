package org.miniwicket;

import java.util.HashMap;
import java.util.Map;

/**
 * Mini Wicket Application - manages the framework lifecycle.
 * Simplified version of Wicket's Application class.
 */
public abstract class Application {
    
    private static Application instance;
    private final Map<Integer, Page> pages = new HashMap<>();
    
    /**
     * Get the application instance
     */
    public static Application get() {
        return instance;
    }
    
    /**
     * Set the application instance
     */
    public static void set(Application application) {
        instance = application;
    }
    
    /**
     * Get the home page class
     */
    public abstract Class<? extends Page> getHomePage();
    
    /**
     * Initialize the application
     */
    public void init() {
        // Override in subclasses for custom initialization
    }
    
    /**
     * Create a new page instance
     */
    public Page createPage(Class<? extends Page> pageClass) {
        try {
            Page page = pageClass.getDeclaredConstructor().newInstance();
            pages.put(page.getPageId(), page);
            return page;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create page: " + pageClass, e);
        }
    }
    
    /**
     * Get a page by ID
     */
    public Page getPage(int pageId) {
        return pages.get(pageId);
    }
    
    /**
     * Process a request for the home page
     */
    public String processHomePageRequest() {
        Page homePage = createPage(getHomePage());
        
        StringResponse response = new StringResponse();
        RequestCycle cycle = new RequestCycle(response);
        RequestCycle.set(cycle);
        
        try {
            homePage.render();
            return response.getBuffer();
        } finally {
            RequestCycle.clear();
        }
    }
}